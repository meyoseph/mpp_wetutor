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
    @DeleteMapping("{profile_id}")
    public ResponseEntity<Object> deleteProfile(@PathVariable("profile_id") String profileId) throws Exception {
        ResponseEntity<Object> response = profileService.deleteProfile(profileId);
        return response;
    }

    // Updates profile in DB
    @GetMapping("/request-approval")
    public ResponseEntity<Object> requestApproval(@RequestParam String profile_id) throws Exception {
        ResponseEntity<Object> response = profileService.request(profile_id);
        return response;
    }

}
