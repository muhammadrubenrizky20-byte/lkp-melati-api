package com.lkpmelati.api.model; // Sesuaikan dengan package project kamu

import jakarta.persistence.*;

@Entity
@Table(name = "media_gallery")
public class MediaGallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String type; // "IMAGE" atau "VIDEO"

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String mediaUrl;

    public MediaGallery() {}

    public MediaGallery(String title, String type, String mediaUrl) {
        this.title = title;
        this.type = type;
        this.mediaUrl = mediaUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getMediaUrl() { return mediaUrl; }
    public void setMediaUrl(String mediaUrl) { this.mediaUrl = mediaUrl; }
}
