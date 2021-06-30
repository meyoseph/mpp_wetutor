package com.example.WeTutor.controllers;

import com.example.WeTutor.requests.ProfileRequest;
import com.example.WeTutor.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllProfiles() throws Throwable {
        ResponseEntity<Object> response = profileService.getAllProfiles();
        return response;
    }

    // Get profile by tutor id
    @GetMapping("/current")
    public ResponseEntity<Object> getProfile() throws Exception {
        ResponseEntity<Object> response = profileService.getProfile();
        return response;
    }

    // Get profile by tutor user name
    @GetMapping("/{tutorId}")
    public ResponseEntity<Object> getProfileByTutorId(@PathVariable("tutorId") String tutorId) throws Exception {
        ResponseEntity<Object> response = profileService.getProfileById(tutorId);
        return response;
    }

    // Creates profile
    @PostMapping("/create-profile")
    public ResponseEntity<Object> createProfile(@RequestBody ProfileRequest profileRequest) {
        ResponseEntity<Object> response = profileService.addProfile(profileRequest);
        return response;
    }

    // Updates profile in DB
    @PostMapping("/update-profile")
    public ResponseEntity<Object> editProfile(@RequestBody ProfileRequest profileRequest) throws Exception {
        ResponseEntity<Object> response = profileService.updateProfile(profileRequest);
        return response;
    }

    // Delete profile in DB
    @GetMapping("/delete-profile-by-tutor-id")
    public ResponseEntity<Object> deleteByTutorId(@RequestParam String tutor_id) {
        ResponseEntity<Object> response = profileService.deleteProfileByTutorId(tutor_id);
        return response;
    }

    // Delete profile in DB
    @DeleteMapping("{profileId}")
    public ResponseEntity<Object> deleteProfile(@PathVariable("profileId") String profileId) throws Exception {
        ResponseEntity<Object> response = profileService.deleteProfile(profileId);
        return response;
    }

    // Updates profile in DB
    @GetMapping("/request-approval/{profileId}")
    public ResponseEntity<Object> requestApproval(@PathVariable("profileId") String profileId) throws Exception {
        ResponseEntity<Object> response = profileService.request(profileId);
        return response;
    }

}
