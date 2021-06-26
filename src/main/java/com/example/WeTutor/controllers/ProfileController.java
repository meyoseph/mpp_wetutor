package com.example.WeTutor.controllers;

import com.example.WeTutor.entities.Profile;
import com.example.WeTutor.entities.Tutor;
import com.example.WeTutor.entities.User;
import com.example.WeTutor.repositories.TutorRepository;
import com.example.WeTutor.requests.AuthRequest;
import com.example.WeTutor.requests.LoggedInUserRequest;
import com.example.WeTutor.requests.ProfileRequest;
import com.example.WeTutor.requests.RegistrationRequest;
import com.example.WeTutor.services.LoginService;
import com.example.WeTutor.services.ProfileService;
import com.example.WeTutor.services.RegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/profile")
public class ProfileController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private ProfileService profileService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllProfiles() throws Throwable {
        ResponseEntity<Object> response = profileService.getAllProfiles();
        return response;
    }

    @GetMapping("/")
    public ResponseEntity<Object> getProfile(@RequestParam String tutor_id) throws Exception {
        ResponseEntity<Object> response = profileService.getProfile(tutor_id);
        return response;
    }

    // Creates profile
    @PostMapping("/create-profile")
    public ResponseEntity<Object> createProfile(@RequestBody ProfileRequest profileRequest) throws Exception {
        ResponseEntity<Object> response = profileService.addProfile(profileRequest);
        return response;
    }

    // Updates profile in DB
    @PostMapping("/update-profile")
    public ResponseEntity<Object> register(@RequestBody ProfileRequest profileRequest) throws Exception {
        ResponseEntity<Object> response = profileService.updateProfile(profileRequest);
        return response;
    }

    // Delete profile in DB
    @GetMapping("/delete-profile-by-tutor-id")
    public ResponseEntity<Object> deleteByTutorId(@RequestParam String tutor_id) throws Exception {
        ResponseEntity<Object> response = profileService.deleteProfileByTutorId(tutor_id);
        return response;
    }

    // Delete profile in DB
    @GetMapping("/delete-profile")
    public ResponseEntity<Object> deleteProfile(@RequestParam String profile_id) throws Exception {
        ResponseEntity<Object> response = profileService.deleteProfile(profile_id);
        return response;
    }

}
