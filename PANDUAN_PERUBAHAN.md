# Panduan Setelah Update Ini

## 1. Cara jalanin
Sama seperti biasa: jalankan lewat VS Code / `mvnw spring-boot:run`, pastikan XAMPP MySQL nyala.
Hibernate otomatis bikin tabel baru (`gallery`) dan kolom baru (`programs.image_url`) karena
`spring.jpa.hibernate.ddl-auto=update`. Tidak perlu bikin SQL manual.

## 2. Alamat panel admin sekarang
**http://localhost:8080/kelola-x92fp**

Bukan lagi `/admin` atau `/admin.html` — dua alamat itu sekarang sudah tidak ada.
URL ini sengaja tidak dilink dari mana pun di website publik. Simpan alamat ini baik-baik
(bisa di-bookmark di browser lo sendiri).

Mau ganti jadi alamat lain? Dua tempat yang perlu diubah bareng:
1. `src/main/resources/application.properties` → ganti `app.admin-path=kelola-x92fp`
2. Rename file `src/main/resources/static/kelola-x92fp.html` jadi nama yang sama

## 3. Login admin
Sekarang panel admin **wajib login** — sebelumnya bisa diakses siapa saja tanpa password sama sekali.

- Kalau di database (tabel `users`) sudah ada akun dengan `role = ROLE_ADMIN`, langsung dipakai
  untuk login di halaman panel.
- Kalau **belum ada** akun admin sama sekali, buat satu manual dulu lewat MySQL (phpMyAdmin/HeidiSQL),
  karena endpoint register sekarang sengaja dikunci hanya untuk admin yang sudah login (supaya orang
  luar tidak bisa daftar jadi admin sendiri). Cara paling gampang:
  1. Jalankan aplikasinya sekali (biar tabel `users` otomatis kebuat).
  2. Di phpMyAdmin, insert 1 baris ke tabel `users` dengan `role = ROLE_ADMIN`. Untuk kolom `password`,
     isi dengan hasil BCrypt hash (jangan plain text) — paling gampang minta saya generate-in hash-nya
     kapan aja lo mau set/reset password admin.

## 4. Yang berubah di balik layar
- **Keamanan**: sebelumnya SEMUA endpoint API bisa diakses tanpa login (`permitAll` total). Sekarang
  endpoint yang mengubah data (`Kelola Program`, `Kelola User`, `Dashboard Pendaftaran`, `Pengaturan
  Website`, upload file) wajib token login admin yang valid.
- **Celah keamanan register**: sebelumnya endpoint `/api/auth/register` menerima field `role` dari
  request — artinya siapa saja bisa daftar akun dan bikin dirinya `ROLE_ADMIN` sendiri. Sudah diperbaiki,
  sekarang role selalu di-set `ROLE_USER` dan endpoint ini sendiri sekarang hanya bisa dipanggil admin
  yang sudah login.
- **Website publik terhubung ke admin**: `index.html` sekarang mengambil Nama Website, Judul Hero, dan
  Nomor WhatsApp dari `Pengaturan Website` secara otomatis. Bagian **Galeri Kegiatan** sekarang juga
  dinamis — foto lama otomatis dipindah ke database saat pertama kali dijalankan, dan bisa
  ditambah/dihapus dari panel admin (tab **Galeri Kegiatan**), termasuk **upload video**.
- **Kelola Program**: sekarang ada form tambah/edit/hapus program lengkap dengan upload foto, bukan cuma
  tabel lihat-lihat doang.
- File yang diupload disimpan di folder `uploads/` (folder baru, sejajar dengan `src/`) — sengaja di luar
  `target/` supaya tidak ikut hilang tiap build ulang.

## 5. Belum disentuh (opsional buat lanjutan)
Bagian **Program Kursus**, **Struktur Pengajar**, **Fasilitas**, **Sertifikat**, dan **Testimoni** di
`index.html` masih teks statis (belum ditarik dari database), karena scope-nya besar. Kalau nanti mau
bagian-bagian itu juga bisa diedit dari admin, tinggal bilang aja.
