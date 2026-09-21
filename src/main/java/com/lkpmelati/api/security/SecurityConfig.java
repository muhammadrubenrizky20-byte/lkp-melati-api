package com.lkpmelati.api.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${app.admin-path}")
    private String adminPath;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // 0. Preflight CORS
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // 1. File static (HTML, gambar, video, CSS, JS) & hasil upload -> publik
                .requestMatchers(
                    "/",
                    "/*.html", "/*.css", "/*.js", "/*.ico",
                    "/*.jpg", "/*.jpeg", "/*.png", "/*.webp", "/*.gif",
                    "/*.mp4", "/*.webm", "/*.mov",
                    "/uploads/**",
                    "/static/**", "/css/**", "/js/**", "/images/**"
                ).permitAll()

                // 2. Alamat rahasia panel admin (redirect ke file .html-nya; loginnya ada di halaman itu)
                .requestMatchers("/" + adminPath).permitAll()

                // 3. Baca data & login -> publik
                .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()

                // 4. Semua yang mengubah data (POST/PUT/DELETE, upload, register) -> wajib ADMIN
                .requestMatchers("/api/**").hasAuthority("ROLE_ADMIN")

                .anyRequest().authenticated()
            )
            // Filter JWT harus ada DI DALAM rantai Spring Security supaya token dibaca
            // sebelum aturan otorisasi di atas dicek.
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // JwtAuthFilter adalah @Component, jadi Spring Boot otomatis mendaftarkannya juga
    // sebagai filter servlet biasa. Matikan pendaftaran otomatis itu supaya hanya jalan
    // di dalam rantai Spring Security (lewat addFilterBefore di atas).
    @Bean
    public FilterRegistrationBean<JwtAuthFilter> jwtAuthFilterRegistration(JwtAuthFilter filter) {
        FilterRegistrationBean<JwtAuthFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
