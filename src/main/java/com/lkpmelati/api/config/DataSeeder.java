package com.lkpmelati.api.config;

import com.lkpmelati.api.model.MediaGallery;
import com.lkpmelati.api.model.Course;
import com.lkpmelati.api.repository.MediaGalleryRepository;
import com.lkpmelati.api.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final MediaGalleryRepository mediaGalleryRepository;
    private final CourseRepository courseRepository;

    public DataSeeder(MediaGalleryRepository mediaGalleryRepository, CourseRepository courseRepository) {
        this.mediaGalleryRepository = mediaGalleryRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) {
        seedGallery();
        seedCourse();
    }

    private void seedGallery() {
        if (mediaGalleryRepository.count() > 0) {
            return; 
        }

        String[][] seedData = {
                {"Praktik Menjahit Busana", "Tata Busana", "/KEGIATAN MEJAHIT.jpg"},
                {"Tata Rias Wajah", "Tata Rias", "/KEGIATAN TATA RIAS.jpg"},
                {"Praktik Rias Pengantin", "Tata Rias", "/KEGIATAN TATA RIAS 2.jpg"},
                {"Sesi Kecantikan", "Tata Rias", "/KEGIATAN TATA RIAS 3.jpg"},
                {"Uji Kompetensi Rias", "Tata Rias", "/KEGIATAN TATA RIAS 4.jpg"},
                {"Fotografi Dasar", "Fotografi", "/KEGIATAN FOTOGRAFI DASAR.jpg"},
                {"Praktik Kamera", "Fotografi", "/KEGIATAN FOTOGRAFI DASAR 2.jpg"},
                {"Sesi Pemotretan", "Fotografi", "/KEGIATAN FOTOGRAFI DASAR 3.jpg"},
                {"Teknik Komposisi", "Fotografi", "/KEGIATAN FOTOGRAFI DASAR 4.jpg"},
                {"Kerajinan Tangan (Handicraft)", "Handicraft", "/HANDICRAFT.jpg"},
                {"Kreasi Kriya Unik", "Handicraft", "/HANDICRAFT 2.jpg"},
                {"Praktik Tata Boga", "Tata Boga", "/KEGIATAN OLAHAN MAKANAN.jpg"},
                {"Kreasi Kuliner & Pastry", "Tata Boga", "/KEGIATAN OLAHAN MAKANAN 3.jpg"},
                {"Pengemasan Produk Boga", "Tata Boga", "/KEGIATAN OLAHAN MAKANAN 5.jpg"},
        };

        for (String[] row : seedData) {
            MediaGallery g = new MediaGallery();
            g.setTitle(row[0]);
            g.setCategory(row[1]);
            g.setMediaUrl(row[2]);
            g.setMediaType("PHOTO");
            mediaGalleryRepository.save(g);
        }
    }

    private void seedCourse() {
        if (courseRepository.count() > 0) {
            return; 
        }

        Course tataBusana = new Course();
        tataBusana.setNamaProgram("a. Tata Busana");
        tataBusana.setIcon("bi-scissors");
        tataBusana.setDeskripsi("Program lengkap dari tingkat dasar hingga mahir dan keahlian khusus:");
        tataBusana.setPoin(String.join("\n",
                "Reguler Level 2",
                "Reguler Level 3",
                "Privat Go to Home",
                "Privat 6 bulan (Tanpa Uang Pangkal)",
                "Privat Singkat dan Garmen (Tanpa Uang Pangkal)",
                "Kain Wiron dan Sarung Tanpa Potong",
                "Kebaya Modifikasi",
                "Keterampilan Terkait"
        ));
        tataBusana.setUrutan(1);
        courseRepository.save(tataBusana);

        Course handicraft = new Course();
        handicraft.setNamaProgram("b. Handicraft");
        handicraft.setIcon("bi-palette");
        handicraft.setDeskripsi("Pelatihan kerajinan tangan kreatif dan bernilai seni tinggi.");
        handicraft.setUrutan(2);
        courseRepository.save(handicraft);

        Course fotografi = new Course();
        fotografi.setNamaProgram("c. Photografi");
        fotografi.setIcon("bi-camera-fill");
        fotografi.setDeskripsi("Teknik dasar hingga pemotretan profesional.");
        fotografi.setUrutan(3);
        courseRepository.save(fotografi);

        Course riasBoga = new Course();
        riasBoga.setNamaProgram("d. Tata Rias Pengantin & e. Tata Boga");
        riasBoga.setIcon("bi-brush");
        riasBoga.setDeskripsi("Keahlian rias pengantin tradisional/modern serta olahan kuliner.");
        riasBoga.setUrutan(4);
        courseRepository.save(riasBoga);
    }
}