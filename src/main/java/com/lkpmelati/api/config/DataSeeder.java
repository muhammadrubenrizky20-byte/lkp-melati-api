package com.lkpmelati.api.config;

import com.lkpmelati.api.model.Gallery;
import com.lkpmelati.api.model.Program;
import com.lkpmelati.api.repository.GalleryRepository;
import com.lkpmelati.api.repository.ProgramRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Supaya foto-foto kegiatan yang tadinya hardcode langsung di index.html
 * TIDAK HILANG setelah bagian Galeri diganti jadi dinamis -- begitu aplikasi
 * pertama kali jalan dan tabel "gallery" masih kosong, data ini otomatis
 * dimasukkan. Setelah itu semuanya bisa diedit/dihapus/ditambah lewat panel admin.
 *
 * File foto aslinya tetap ada di /static (tidak dihapus), jadi seeder ini
 * cukup mereferensikan nama filenya saja.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final GalleryRepository galleryRepository;
    private final ProgramRepository programRepository;

    public DataSeeder(GalleryRepository galleryRepository, ProgramRepository programRepository) {
        this.galleryRepository = galleryRepository;
        this.programRepository = programRepository;
    }

    @Override
    public void run(String... args) {
        seedGallery();
        seedProgram();
    }

    private void seedGallery() {
        if (galleryRepository.count() > 0) {
            return; // sudah pernah diisi / sudah dikelola manual oleh admin, jangan timpa
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
            Gallery g = new Gallery();
            g.setTitle(row[0]);
            g.setCategory(row[1]);
            g.setMediaUrl(row[2]);
            g.setMediaType("PHOTO");
            galleryRepository.save(g);
        }
    }

    /**
     * Supaya isi section "Daftar Program Kursus" yang tadinya hardcode langsung
     * di index.html TIDAK HILANG setelah bagian ini diganti jadi dinamis --
     * begitu aplikasi pertama kali jalan dan tabel "programs" masih kosong,
     * data ini otomatis dimasukkan persis seperti tampilan lama. Setelah itu
     * semuanya bisa diedit/dihapus/ditambah (termasuk video) lewat panel admin.
     */
    private void seedProgram() {
        if (programRepository.count() > 0) {
            return; // sudah pernah diisi / sudah dikelola manual oleh admin, jangan timpa
        }

        // a. Tata Busana -- kartu detail dengan daftar poin
        Program tataBusana = new Program();
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
        programRepository.save(tataBusana);

        // b. Handicraft -- kartu ringkas
        Program handicraft = new Program();
        handicraft.setNamaProgram("b. Handicraft");
        handicraft.setIcon("bi-palette");
        handicraft.setDeskripsi("Pelatihan kerajinan tangan kreatif dan bernilai seni tinggi.");
        handicraft.setUrutan(2);
        programRepository.save(handicraft);

        // c. Photografi -- kartu ringkas
        Program fotografi = new Program();
        fotografi.setNamaProgram("c. Photografi");
        fotografi.setIcon("bi-camera-fill");
        fotografi.setDeskripsi("Teknik dasar hingga pemotretan profesional.");
        fotografi.setUrutan(3);
        programRepository.save(fotografi);

        // d. Tata Rias Pengantin & e. Tata Boga -- kartu ringkas
        Program riasBoga = new Program();
        riasBoga.setNamaProgram("d. Tata Rias Pengantin & e. Tata Boga");
        riasBoga.setIcon("bi-brush");
        riasBoga.setDeskripsi("Keahlian rias pengantin tradisional/modern serta olahan kuliner.");
        riasBoga.setUrutan(4);
        programRepository.save(riasBoga);
    }
}