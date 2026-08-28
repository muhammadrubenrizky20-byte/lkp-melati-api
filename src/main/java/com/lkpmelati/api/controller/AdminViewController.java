package com.lkpmelati.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminViewController {

    // Path rahasia diambil dari application.properties (app.admin-path),
    // supaya gampang diganti tanpa compile ulang logic-nya, cukup ganti
    // property + nama file HTML fisiknya di /static.
    @Value("${app.admin-path}")
    private String adminPath;

    @GetMapping("/${app.admin-path}")
    public String adminPage() {
        return "redirect:/" + adminPath + ".html";
    }
}
