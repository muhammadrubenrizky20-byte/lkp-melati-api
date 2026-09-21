package com.lkpmelati.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaProgram;
    private String icon;

    @Column(columnDefinition = "TEXT")
    private String deskripsi;

    @Column(columnDefinition = "TEXT")
    private String poin;

    private Double harga;

    private String jadwal;

    private String imageUrl;

    private String videoUrl;

    private Integer urutan = 0;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNamaProgram() { return namaProgram; }
    public void setNamaProgram(String namaProgram) { this.namaProgram = namaProgram; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public String getPoin() { return poin; }
    public void setPoin(String poin) { this.poin = poin; }

    public Integer getUrutan() { return urutan; }
    public void setUrutan(Integer urutan) { this.urutan = urutan; }

    public Double getHarga() { return harga; }
    public void setHarga(Double harga) { this.harga = harga; }

    public String getJadwal() { return jadwal; }
    public void setJadwal(String jadwal) { this.jadwal = jadwal; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }
}
