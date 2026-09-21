package com.lkpmelati.api.repository;

import com.lkpmelati.api.model.SiteSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteSettingRepository extends JpaRepository<SiteSetting, Integer> {
}