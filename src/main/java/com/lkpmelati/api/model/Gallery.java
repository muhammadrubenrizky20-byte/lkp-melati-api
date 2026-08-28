package com.lkpmelati.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "gallery")
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String category;      // contoh: "Tata Busana", "Handicraft", "Tata Boga", dll
    private String mediaUrl;      // path hasil upload, misal "/uploads/xxxx.jpg"
    private String mediaType;     // "PHOTO" atau "VIDEO"
}
