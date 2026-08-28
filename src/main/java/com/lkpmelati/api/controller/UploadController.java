package com.lkpmelati.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "*")
public class UploadController {

    @Value("${app.upload-dir}")
    private String uploadDir;

    // Tipe file yang diizinkan diupload (foto & video umum saja)
    private static final java.util.Set<String> ALLOWED_EXT = java.util.Set.of(
            "jpg", "jpeg", "png", "gif", "webp", "mp4", "webm", "mov"
    );

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "File kosong!"));
        }

        String originalName = file.getOriginalFilename() != null ? file.getOriginalFilename() : "file";
        String ext = "";
        int dotIndex = originalName.lastIndexOf('.');
        if (dotIndex >= 0) {
            ext = originalName.substring(dotIndex + 1).toLowerCase();
        }

        if (!ALLOWED_EXT.contains(ext)) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Tipe file tidak didukung. Gunakan foto (jpg/png/webp/gif) atau video (mp4/webm/mov)."
            ));
        }

        try {
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String safeName = UUID.randomUUID() + "." + ext;
            Path targetPath = Path.of(uploadDir, safeName);
            file.transferTo(targetPath);

            String mediaType = java.util.Set.of("mp4", "webm", "mov").contains(ext) ? "VIDEO" : "PHOTO";

            return ResponseEntity.ok(Map.of(
                    "url", "/uploads/" + safeName,
                    "mediaType", mediaType
            ));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Gagal menyimpan file: " + e.getMessage()));
        }
    }
}
