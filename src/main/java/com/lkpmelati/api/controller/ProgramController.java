package com.lkpmelati.api.controller;

import com.lkpmelati.api.model.Program;
import com.lkpmelati.api.repository.ProgramRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/program")
public class ProgramController { 

    private final ProgramRepository programRepository;

    public ProgramController(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    // 1. GET ALL (Ambil Semua Data)
    // Diurutkan berdasarkan "urutan" supaya urutan tampil di halaman utama
    // bisa diatur dari admin panel (bukan sekedar urutan ID).
    @GetMapping
    public List<Program> getAllPrograms() {
        return programRepository.findAll(Sort.by(Sort.Direction.ASC, "urutan", "id"));
    }

    // 2. GET BY ID (Ambil 1 Data Berdasarkan ID)
    @GetMapping("/{id}")
    public Program getProgramById(@PathVariable Long id) {
        return programRepository.findById(id).orElse(null);
    }

    // 3. POST (Tambah Data Baru)
    @PostMapping
    public Program createProgram(@RequestBody Program program) {
        return programRepository.save(program);
    }

    // 4. PUT (Edit/Update Data)
    @PutMapping("/{id}")
    public Program updateProgram(@PathVariable Long id, @RequestBody Program programDetails) {
        Program program = programRepository.findById(id).orElse(null);
        if (program != null) {
            program.setNamaProgram(programDetails.getNamaProgram());
            program.setDeskripsi(programDetails.getDeskripsi());
            program.setHarga(programDetails.getHarga());
            program.setJadwal(programDetails.getJadwal());
            program.setImageUrl(programDetails.getImageUrl());
            program.setIcon(programDetails.getIcon());
            program.setPoin(programDetails.getPoin());
            program.setVideoUrl(programDetails.getVideoUrl());
            if (programDetails.getUrutan() != null) {
                program.setUrutan(programDetails.getUrutan());
            }
            return programRepository.save(program);
        }
        return null;
    }

    // 5. DELETE (Hapus Data)
    @DeleteMapping("/{id}")
    public String deleteProgram(@PathVariable Long id) {
        programRepository.deleteById(id);
        return "Data program dengan ID " + id + " berhasil dihapus!";
    }
}