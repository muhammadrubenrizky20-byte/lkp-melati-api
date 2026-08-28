package com.lkpmelati.api.controller;

import com.lkpmelati.api.model.User;
import com.lkpmelati.api.repository.UserRepository;
import com.lkpmelati.api.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username sudah digunakan!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // PENTING: jangan pernah percaya field "role" yang dikirim client.
        // Endpoint ini sekarang hanya bisa diakses oleh admin yang sudah login
        // (lihat SecurityConfig), jadi kalau admin memang mau bikin akun admin baru,
        // set role-nya di sini secara eksplisit, bukan dari request body.
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return ResponseEntity.ok("Registrasi berhasil!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginData) {
        Optional<User> userOpt = userRepository.findByUsername(loginData.getUsername());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(loginData.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("username", user.getUsername());
                response.put("role", user.getRole());
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(401).body("Username atau password salah!");
    }
}