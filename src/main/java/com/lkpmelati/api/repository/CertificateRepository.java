package com.lkpmelati.api.repository;

import com.lkpmelati.api.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}