package com.lkpmelati.api.repository;

import com.lkpmelati.api.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
}