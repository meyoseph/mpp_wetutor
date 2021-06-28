package com.example.WeTutor.services;

import com.example.WeTutor.entities.*;
import com.example.WeTutor.repositories.ProfileRepository;
import com.example.WeTutor.repositories.TutorRepository;
import com.example.WeTutor.repositories.UserRepository;
import com.example.WeTutor.requests.ProfileRequest;
import com.example.WeTutor.requests.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileService {

    ProfileRepository profileRepository;
    TutorRepository tutorRepository;

    public ResponseEntity<Object> getAllProfiles() {
        JSONObject responseObject = new JSONObject();

        List<Profile> profiles = profileRepository.findAll();
        if(profiles != null){
            responseObject.put("success",true);
            responseObject.put("profiles", profiles);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }else{
            responseObject.put("error",true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

    }

    public ResponseEntity<Object> getProfile(String tutorId) {
        JSONObject responseObject = new JSONObject();

        Optional<Profile> profile = profileRepository.findByTutorId(tutorId);

        responseObject.put("success",true);
        responseObject.put("profile", profile.get());

        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

    public ResponseEntity<Object> addProfile(ProfileRequest profileRequest) {
        JSONObject responseObject = new JSONObject();

        List<Profile> profiles = profileRepository.findAll();
        for(Profile profile: profiles ){
            if(profile.getTutor().getId().equals(profileRequest.getTutor().getId())){
                responseObject.put("profile", "Profile has already been built");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
            }
        }

        if(!validateInputs(profileRequest.getAge())){
            responseObject.put("age", "Age is required");
        }
        if(!validateInputs(profileRequest.getFirstName())){
            responseObject.put("first_name", "FirstName is required");
        }
        if(!validateInputs(profileRequest.getLastName())){
            responseObject.put("last_name", "LastName is required");
        }
        if(!validateInputs(profileRequest.getGender())){
            responseObject.put("gender", "Gender is required");
        }
        if(!validateInputs(profileRequest.getPhoneNumber())){
            responseObject.put("phone_number", "PhoneNumber is required");
        }
        if(!validateInputs(profileRequest.getLocation())){
            responseObject.put("location", "Location is required");
        }
        if(!validateInputs(profileRequest.getMotive())){
            responseObject.put("motive", "Motive is required");
        }
        if(!validateInputs(profileRequest.getMajorSubject())){
            responseObject.put("major_subject", "MajorSubject is required");
        }
        if(!validateInputs(profileRequest.getSubjects())){
            responseObject.put("subjects", "Subjects is required");
        }
        if(!validateInputs(profileRequest.getEducations())){
            responseObject.put("educations", "Educations is required");
        }
        if(!validateInputs(profileRequest.getWorkExperiences())){
            responseObject.put("work_experiences", "WorkExperiences is required");
        }
        if(!validateInputs(profileRequest.getLanguages())){
            responseObject.put("languages", "Languages is required");
        }
        if(profileRequest.getTutor() == null){
            responseObject.put("tutor", "Tutor can not be null");
        }

        if(responseObject.isEmpty()){
            Profile profile = new Profile(profileRequest.getAge(), profileRequest.getFirstName(), profileRequest.getLastName(), profileRequest.getGender(), profileRequest.getPhoneNumber(), profileRequest.getLocation(), profileRequest.getMotive(), profileRequest.getMajorSubject(), profileRequest.getSubjects(), profileRequest.getEducations(), profileRequest.getWorkExperiences(), profileRequest.getLanguages(), profileRequest.getTutor());
            profileRepository.save(profile);
            responseObject.put("success",true);
            responseObject.put("message","Profile created successfully");

            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

    }

    public ResponseEntity<Object> updateProfile(ProfileRequest profileRequest) {
        JSONObject responseObject = new JSONObject();

        boolean flag = false;
        List<Profile> profiles = profileRepository.findAll();
        for(Profile profile: profiles ){
            if(profile.getTutor().getId().equals(profileRequest.getTutor().getId())){
                flag = true;
            }
        }
        if(!flag) {
            responseObject.put("profile", "Profile doesn't exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

        if(!validateInputs(profileRequest.getAge())){
            responseObject.put("age", "Age is required");
        }
        if(!validateInputs(profileRequest.getFirstName())){
            responseObject.put("first_name", "FirstName is required");
        }
        if(!validateInputs(profileRequest.getLastName())){
            responseObject.put("last_name", "LastName is required");
        }
        if(!validateInputs(profileRequest.getGender())){
            responseObject.put("gender", "Gender is required");
        }
        if(!validateInputs(profileRequest.getPhoneNumber())){
            responseObject.put("phone_number", "PhoneNumber is required");
        }
        if(!validateInputs(profileRequest.getLocation())){
            responseObject.put("location", "Location is required");
        }
        if(!validateInputs(profileRequest.getMotive())){
            responseObject.put("motive", "Motive is required");
        }
        if(!validateInputs(profileRequest.getMajorSubject())){
            responseObject.put("major_subject", "MajorSubject is required");
        }
        if(!validateInputs(profileRequest.getSubjects())){
            responseObject.put("subjects", "Subjects is required");
        }
        if(!validateInputs(profileRequest.getEducations())){
            responseObject.put("educations", "Educations is required");
        }
        if(!validateInputs(profileRequest.getWorkExperiences())){
            responseObject.put("work_experiences", "WorkExperiences is required");
        }
        if(!validateInputs(profileRequest.getLanguages())){
            responseObject.put("languages", "Languages is required");
        }

        if(responseObject.isEmpty()){
            Optional<Profile> optionalProfile = profileRepository.findByTutorId(profileRequest.getTutor().getTutorId());
            Profile profile = optionalProfile.get();
            profile.setAge(profileRequest.getAge());
            profile.setFirstName(profileRequest.getFirstName());
            profile.setLastName(profileRequest.getLastName());
            profile.setGender(profileRequest.getGender());
            profile.setPhoneNumber(profileRequest.getPhoneNumber());
            profile.setLocation(profileRequest.getLocation());
            profile.setMotive(profileRequest.getMotive());
            profile.setMajorSubject(profileRequest.getMajorSubject());
            profile.setSubjects(profileRequest.getSubjects());
            profile.setEducations(profileRequest.getEducations());
            profile.setWorkExperiences(profileRequest.getWorkExperiences());
            profile.setLanguages(profileRequest.getLanguages());
            profile.setTutor(profileRequest.getTutor());
            profileRepository.save(profile);
            responseObject.put("success", true);
            responseObject.put("message","Profile updated successfully");

            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

    }

    public ResponseEntity<Object> deleteProfile(String profileId) {
        JSONObject responseObject = new JSONObject();

        boolean flag = false;
        List<Profile> profiles = profileRepository.findAll();
        for(Profile profile: profiles ){
            if(profile.getProfileId().equals(profileId)){
                flag = true;
            }
        }
        if(!flag) {
            responseObject.put("profile", "Profile doesn't exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

        if(responseObject.isEmpty()){
            Optional<Profile> profile = profileRepository.findByProfileId(profileId);
            responseObject.put("success", true);
            responseObject.put("message","Profile updated successfully");

            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

    }

    public ResponseEntity<Object> deleteProfileByTutorId(String tutor_id) {
        JSONObject responseObject = new JSONObject();

        boolean flag = false;
        List<Profile> profiles = profileRepository.findAll();
        for(Profile profile: profiles ){
            if(profile.getTutor().getId().equals(tutor_id)){
                flag = true;
            }
        }
        if(!flag) {
            responseObject.put("profile", "Profile doesn't exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

        if(responseObject.isEmpty()){
            Optional<Profile> optionalProfile = profileRepository.findByTutorId(tutor_id);
            Profile profile = optionalProfile.get();
            profileRepository.delete(profile);
            responseObject.put("success", true);
            responseObject.put("message","Profile updated successfully");

            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    public boolean validateInputs(String input){
        if(input == null || input == ""){
            return false;
        } else return true;
    }

}
