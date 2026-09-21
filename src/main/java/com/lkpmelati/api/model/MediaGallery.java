package com.lkpmelati.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "media_gallery")
public class MediaGallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // contoh: "Tata Busana", "Handicraft", "Tata Boga", dll
    private String category;

    // path hasil upload atau file static, misal "/uploads/xxxx.jpg"
    private String mediaUrl;

    // "PHOTO" atau "VIDEO"
    private String mediaType;

    public MediaGallery() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getMediaUrl() { return mediaUrl; }
    public void setMediaUrl(String mediaUrl) { this.mediaUrl = mediaUrl; }

    public String getMediaType() { return mediaType; }
    public void setMediaType(String mediaType) { this.mediaType = mediaType; }
}
