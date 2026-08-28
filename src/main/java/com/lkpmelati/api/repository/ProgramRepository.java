package com.lkpmelati.api.repository;

import com.lkpmelati.api.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
}