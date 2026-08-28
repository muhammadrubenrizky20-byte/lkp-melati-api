package com.lkpmelati.api.security;

import org.springframework.beans.factory.annotation.Value;
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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Path rahasia menuju panel admin. Ganti nilai default di application.properties
    // (app.admin-path) kalau mau diganti sewaktu-waktu -- tapi ingat nama file HTML
    // fisiknya di /static juga harus disesuaikan (lihat AdminViewController).
    @Value("${app.admin-path}")
    private String adminPath;

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // --- Publik: dibutuhkan supaya website utama & login tetap jalan ---
                .requestMatchers("/api/auth/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/settings", "/api/program/**", "/api/gallery/**").permitAll()
                .requestMatchers("/", "/index.html", "/" + adminPath, "/" + adminPath + ".html").permitAll()
                .requestMatchers("/*.css", "/*.js", "/*.png", "/*.jpg", "/*.jpeg", "/*.svg", "/*.ico").permitAll()
                .requestMatchers("/uploads/**").permitAll()

                // --- Wajib login sebagai ADMIN: semua endpoint yang mengubah/melihat data sensitif ---
                .requestMatchers("/api/auth/register").hasRole("ADMIN")
                .requestMatchers("/api/upload/**").hasRole("ADMIN")
                .requestMatchers("/api/users/**").hasRole("ADMIN")
                .requestMatchers("/api/pendaftaran/**").hasRole("ADMIN")
                .requestMatchers("/api/export/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/settings", "/api/program", "/api/gallery").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/settings", "/api/program/**", "/api/gallery/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/program/**", "/api/gallery/**").hasRole("ADMIN")

                .anyRequest().permitAll()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
