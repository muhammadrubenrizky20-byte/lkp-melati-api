package com.lkpmelati.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User { // Hapus @Data, @NoArgsConstructor, @AllArgsConstructor

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;

    private String email;

    // Dibuat nullable karena entity ini juga dipakai oleh UserController
    // untuk CRUD sederhana (nama + email saja, tanpa login).
    @Column(unique = true)
    private String username;

    private String password;

    private String role;

    // Konstruktor Kosong (No-Args)
    public User() {}

    // Konstruktor Lengkap (All-Args)
    public User(Long id, String nama, String username, String password, String role) {
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getter dan Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNama() { return nama != null ? nama : username; }
    public void setNama(String nama) { this.nama = nama; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}