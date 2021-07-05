package com.example.WeTutor.controllers;

import com.example.WeTutor.requests.ProfileRequest;
import com.example.WeTutor.services.ProfileService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/tutors")
    public ResponseEntity<Object> getProfiles() throws Throwable {
        ResponseEntity<Object> response = profileService.getProfiles();
        return response;
    }

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
        System.out.println("Profile response"+response);
        return response;
    }

    @GetMapping("/{tutorUserName}")
    public ResponseEntity<Object> getProfileByTutorUserName(@PathVariable("tutorUserName") String tutorUserName) throws Exception {
    	ResponseEntity<Object> response = profileService.getProfileByTutorUserName(tutorUserName);
        return response;
    }
    
    @GetMapping("/{tutorLocation}")
    public ResponseEntity<Object> getProfileByTutorLocation(@PathVariable("tutorLocation") String tutorLocation) throws Exception{
    	ResponseEntity<Object> response = profileService.getProfileByTutorLocation(tutorLocation);
    	return response;
    }
    
    @GetMapping("/{tutorSubjects}")
    public ResponseEntity<Object> getProfileByTutorSubjects(@PathVariable("tutorSubjects") String tutorSubjects) throws Exception{
    	ResponseEntity<Object> response = profileService.getProfileByTutorSubjects(tutorSubjects);
    	return response;
    } 
    
    @GetMapping("/{tutorLanguages}")
    public ResponseEntity<Object> getProfileByTutorLanguages(@PathVariable("tutorLanguages") String tutorLanguages) throws Exception{
    	ResponseEntity<Object> response = profileService.getProfileByTutorLanguages(tutorLanguages);
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
