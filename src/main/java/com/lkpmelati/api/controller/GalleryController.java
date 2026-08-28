package com.lkpmelati.api.controller;

import com.lkpmelati.api.model.Gallery;
import com.lkpmelati.api.repository.GalleryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gallery")
@CrossOrigin(origins = "*")
public class GalleryController {

    private final GalleryRepository galleryRepository;

    public GalleryController(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    // Publik -- dipakai website utama buat nampilin galeri
    @GetMapping
    public List<Gallery> getAll() {
        return galleryRepository.findAll();
    }

    // Wajib login admin (diatur di SecurityConfig)
    @PostMapping
    public Gallery create(@RequestBody Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    @PutMapping("/{id}")
    public Gallery update(@PathVariable Long id, @RequestBody Gallery details) {
        Gallery gallery = galleryRepository.findById(id).orElse(new Gallery());
        gallery.setTitle(details.getTitle());
        gallery.setCategory(details.getCategory());
        if (details.getMediaUrl() != null) gallery.setMediaUrl(details.getMediaUrl());
        if (details.getMediaType() != null) gallery.setMediaType(details.getMediaType());
        return galleryRepository.save(gallery);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        galleryRepository.deleteById(id);
        return "Item galeri berhasil dihapus!";
    }
}
