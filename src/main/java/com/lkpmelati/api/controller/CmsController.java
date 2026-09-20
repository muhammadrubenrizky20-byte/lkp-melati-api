package com.lkpmelati.api.controller;

import com.lkpmelati.api.model.*;
import com.lkpmelati.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CmsController {

    @Autowired private CourseRepository courseRepo;
    @Autowired private InstructorRepository instructorRepo;
    @Autowired private FacilityRepository facilityRepo;
    @Autowired private MediaGalleryRepository mediaRepo;
    @Autowired private CertificateRepository certRepo;
    @Autowired private TestimonialRepository testimonialRepo;
    @Autowired private SiteSettingRepository settingRepo;

    // --- COURSES ---
    @GetMapping("/courses") public List<Course> getCourses() { return courseRepo.findAll(); }
    @PostMapping("/courses") public Course saveCourse(@RequestBody Course c) { return courseRepo.save(c); }
    @DeleteMapping("/courses/{id}") public void deleteCourse(@PathVariable Long id) { courseRepo.deleteById(id); }

    // --- INSTRUCTORS ---
    @GetMapping("/instructors") public List<Instructor> getInstructors() { return instructorRepo.findAll(); }
    @PostMapping("/instructors") public Instructor saveInstructor(@RequestBody Instructor i) { return instructorRepo.save(i); }
    @DeleteMapping("/instructors/{id}") public void deleteInstructor(@PathVariable Long id) { instructorRepo.deleteById(id); }

    // --- FACILITIES ---
    @GetMapping("/facilities") public List<Facility> getFacilities() { return facilityRepo.findAll(); }
    @PostMapping("/facilities") public Facility saveFacility(@RequestBody Facility f) { return facilityRepo.save(f); }
    @DeleteMapping("/facilities/{id}") public void deleteFacility(@PathVariable Long id) { facilityRepo.deleteById(id); }

    // --- GALLERY ---
    @GetMapping("/gallery") public List<MediaGallery> getGallery() { return mediaRepo.findAll(); }
    @PostMapping("/gallery") public MediaGallery saveMedia(@RequestBody MediaGallery m) { return mediaRepo.save(m); }
    @DeleteMapping("/gallery/{id}") public void deleteMedia(@PathVariable Long id) { mediaRepo.deleteById(id); }

    // --- CERTIFICATES ---
    @GetMapping("/certificates") public List<Certificate> getCertificates() { return certRepo.findAll(); }
    @PostMapping("/certificates") public Certificate saveCert(@RequestBody Certificate c) { return certRepo.save(c); }
    @DeleteMapping("/certificates/{id}") public void deleteCert(@PathVariable Long id) { certRepo.deleteById(id); }

    // --- TESTIMONIALS ---
    @GetMapping("/testimonials") public List<Testimonial> getTestimonials() { return testimonialRepo.findAll(); }
    @PostMapping("/testimonials") public Testimonial saveTestimonial(@RequestBody Testimonial t) { return testimonialRepo.save(t); }
    @DeleteMapping("/testimonials/{id}") public void deleteTestimonial(@PathVariable Long id) { testimonialRepo.deleteById(id); }

    // --- SETTINGS ---
    @GetMapping("/settings") public SiteSetting getSettings() { return settingRepo.findById(1).orElse(new SiteSetting()); }
    @PostMapping("/settings") public SiteSetting saveSettings(@RequestBody SiteSetting s) { s.setId(1); return settingRepo.save(s); }
}