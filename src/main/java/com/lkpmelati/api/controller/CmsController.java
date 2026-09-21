package com.lkpmelati.api.controller;

import com.lkpmelati.api.model.*;
import com.lkpmelati.api.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CmsController {

    private final CourseRepository courseRepo;
    private final InstructorRepository instructorRepo;
    private final FacilityRepository facilityRepo;
    private final MediaGalleryRepository mediaRepo;
    private final CertificateRepository certRepo;
    private final TestimonialRepository testimonialRepo;
    private final SiteSettingRepository settingRepo;

    public CmsController(CourseRepository courseRepo,
                         InstructorRepository instructorRepo,
                         FacilityRepository facilityRepo,
                         MediaGalleryRepository mediaRepo,
                         CertificateRepository certRepo,
                         TestimonialRepository testimonialRepo,
                         SiteSettingRepository settingRepo) {
        this.courseRepo = courseRepo;
        this.instructorRepo = instructorRepo;
        this.facilityRepo = facilityRepo;
        this.mediaRepo = mediaRepo;
        this.certRepo = certRepo;
        this.testimonialRepo = testimonialRepo;
        this.settingRepo = settingRepo;
    }

    // --- COURSES (juga bisa diakses lewat /api/program, dipakai panel admin) ---
    @GetMapping({"/courses", "/program"})
    public List<Course> getCourses() {
        return courseRepo.findAll().stream()
                .sorted(Comparator.comparing(Course::getUrutan, Comparator.nullsLast(Comparator.naturalOrder())))
                .toList();
    }

    @PostMapping({"/courses", "/program"})
    public Course saveCourse(@RequestBody Course c) {
        c.setId(null); // POST selalu bikin baris baru
        return courseRepo.save(c);
    }

    @PutMapping({"/courses/{id}", "/program/{id}"})
    public Course updateCourse(@PathVariable("id") Long id, @RequestBody Course c) {
        c.setId(id);
        return courseRepo.save(c);
    }

    @DeleteMapping({"/courses/{id}", "/program/{id}"})
    public void deleteCourse(@PathVariable("id") Long id) { courseRepo.deleteById(id); }

    // --- INSTRUCTORS ---
    @GetMapping("/instructors")
    public List<Instructor> getInstructors() { return instructorRepo.findAll(); }

    @PostMapping("/instructors")
    public Instructor saveInstructor(@RequestBody Instructor i) { return instructorRepo.save(i); }

    @DeleteMapping("/instructors/{id}")
    public void deleteInstructor(@PathVariable("id") Long id) { instructorRepo.deleteById(id); }

    // --- FACILITIES ---
    @GetMapping("/facilities")
    public List<Facility> getFacilities() { return facilityRepo.findAll(); }

    @PostMapping("/facilities")
    public Facility saveFacility(@RequestBody Facility f) { return facilityRepo.save(f); }

    @DeleteMapping("/facilities/{id}")
    public void deleteFacility(@PathVariable("id") Long id) { facilityRepo.deleteById(id); }

    // --- GALLERY ---
    @GetMapping("/gallery")
    public List<MediaGallery> getGallery() { return mediaRepo.findAll(); }

    @PostMapping("/gallery")
    public MediaGallery saveMedia(@RequestBody MediaGallery m) { return mediaRepo.save(m); }

    @DeleteMapping("/gallery/{id}")
    public void deleteMedia(@PathVariable("id") Long id) { mediaRepo.deleteById(id); }

    // --- CERTIFICATES ---
    @GetMapping("/certificates")
    public List<Certificate> getCertificates() { return certRepo.findAll(); }

    @PostMapping("/certificates")
    public Certificate saveCert(@RequestBody Certificate c) { return certRepo.save(c); }

    @DeleteMapping("/certificates/{id}")
    public void deleteCert(@PathVariable("id") Long id) { certRepo.deleteById(id); }

    // --- TESTIMONIALS ---
    @GetMapping("/testimonials")
    public List<Testimonial> getTestimonials() { return testimonialRepo.findAll(); }

    @PostMapping("/testimonials")
    public Testimonial saveTestimonial(@RequestBody Testimonial t) { return testimonialRepo.save(t); }

    @DeleteMapping("/testimonials/{id}")
    public void deleteTestimonial(@PathVariable("id") Long id) { testimonialRepo.deleteById(id); }

    // --- SETTINGS ---
    @GetMapping("/settings")
    public SiteSetting getSettings() { return settingRepo.findById(1).orElseGet(SiteSetting::new); }

    @PostMapping("/settings")
    public SiteSetting saveSettings(@RequestBody SiteSetting s) {
        s.setId(1);
        return settingRepo.save(s);
    }
}
