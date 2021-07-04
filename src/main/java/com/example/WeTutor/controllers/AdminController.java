package com.example.WeTutor.controllers;

import com.example.WeTutor.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Activate profile in DB
    @GetMapping("/approve-profile/{profileId}")
    public ResponseEntity<Object> approveProfile(@PathVariable("profileId") String profileId) throws Exception {
        ResponseEntity<Object> response = adminService.decideOnProfile(profileId, true);
        return response;
    }

    // Block / Deactivate profile in DB
    @GetMapping("/block-profile/{profileId}")
    public ResponseEntity<Object> blockProfile(@PathVariable("profileId") String profileId) throws Exception {
        ResponseEntity<Object> response = adminService.decideOnProfile(profileId, false);
        return response;
    }

    // Retrieves all the tutors
    @GetMapping(path = "/all-tutors")
    public ResponseEntity<Object> allTutors() {
        ResponseEntity<Object> tutors = adminService.getAllTutors();
        return tutors;
    }

    // Retrieves all the parents
    @GetMapping(path = "/all-parents")
    public ResponseEntity<Object> allParents() {
        ResponseEntity<Object> parents = adminService.getAllParents();
        return parents;
    }
}
