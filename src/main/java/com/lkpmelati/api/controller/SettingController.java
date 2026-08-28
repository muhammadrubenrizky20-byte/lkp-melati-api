package com.lkpmelati.api.controller;

import com.lkpmelati.api.model.Setting;
import com.lkpmelati.api.repository.SettingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
@CrossOrigin(origins = "*")
public class SettingController {

    private final SettingRepository settingRepository;

    public SettingController(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @GetMapping
    public ResponseEntity<Setting> getSettings() {
        Setting setting = settingRepository.findById(1L)
                .orElseGet(() -> new Setting("LPK MELATI", "BELAJAR", "089673534162"));
        return ResponseEntity.ok(setting);
    }

    // Mendukung request PUT maupun POST ke /api/settings
    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<Setting> updateSettings(@RequestBody Setting newSetting) {
        Setting setting = settingRepository.findById(1L).orElse(new Setting());
        setting.setId(1L);
        setting.setSiteName(newSetting.getSiteName());
        setting.setHeroTitle(newSetting.getHeroTitle());
        setting.setWhatsappNumber(newSetting.getWhatsappNumber());
        
        Setting savedSetting = settingRepository.save(setting);
        return ResponseEntity.ok(savedSetting);
    }
}