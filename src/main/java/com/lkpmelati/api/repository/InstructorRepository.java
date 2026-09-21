package com.lkpmelati.api.repository;

import com.lkpmelati.api.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}