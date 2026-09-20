package com.lkpmelati.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lkpmelati.api.model.User; // Sesuaikan jika lokasi paket model User kamu berbeda
import com.lkpmelati.api.repository.UserRepository; // Sesuaikan jika lokasi repository kamu berbeda

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ROLE_ADMIN");
                userRepository.save(admin);
                System.out.println(">>> AKUN ADMIN BERHASIL DIBUAT DENGAN USERNAME: admin & PASSWORD: admin123 <<<");
            }
        };
    }
}