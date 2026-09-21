package com.lkpmelati.api.model;

import jakarta.persistence.*;

// Pengaturan website (satu baris saja, id selalu 1).
// Nama field disamakan dengan yang dipakai panel admin & index.html:
// siteName, heroTitle, whatsappNumber.
@Entity
@Table(name = "site_settings")
public class SiteSetting {

    @Id
    private Integer id = 1;

    private String siteName;

    private String heroTitle;

    private String whatsappNumber;

    public SiteSetting() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getSiteName() { return siteName; }
    public void setSiteName(String siteName) { this.siteName = siteName; }

    public String getHeroTitle() { return heroTitle; }
    public void setHeroTitle(String heroTitle) { this.heroTitle = heroTitle; }

    public String getWhatsappNumber() { return whatsappNumber; }
    public void setWhatsappNumber(String whatsappNumber) { this.whatsappNumber = whatsappNumber; }
}
