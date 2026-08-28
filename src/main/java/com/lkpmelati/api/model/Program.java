package com.lkpmelati.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "programs")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaProgram;
    private String deskripsi;
    private Double harga;
    private String jadwal;
    private String imageUrl;

    // Nama icon Bootstrap Icons, contoh: "bi-scissors", "bi-camera-fill".
    // Ditampilkan di depan judul program pada section "Daftar Program Kursus".
    private String icon;

    // Daftar poin/list keahlian di dalam 1 program (dipisah per baris/enter di admin).
    // Kalau field ini diisi, kartu program akan tampil dalam mode "detail" (dengan bullet list).
    // Kalau kosong, kartu tampil ringkas (mode "compact"), hanya judul + deskripsi singkat.
    @Column(columnDefinition = "TEXT")
    private String poin;

    // URL video (hasil upload lewat /api/upload) yang ditampilkan di dalam kartu program ini.
    // Opsional -- boleh dikosongkan kalau program tidak punya video.
    private String videoUrl;

    // Urutan tampil di halaman utama (angka kecil tampil duluan). Default 0.
    private Integer urutan = 0;
}