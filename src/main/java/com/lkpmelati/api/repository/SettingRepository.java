package com.lkpmelati.api.repository;

import com.lkpmelati.api.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Setting, Long> {
}