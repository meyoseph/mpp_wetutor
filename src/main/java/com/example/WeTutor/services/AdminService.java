package com.example.WeTutor.services;

import com.example.WeTutor.CustomResponses.CustomTutorResponse;
import com.example.WeTutor.entities.Profile;
import com.example.WeTutor.entities.ProfileState;
import com.example.WeTutor.entities.User;
import com.example.WeTutor.repositories.ProfileRepository;
import com.example.WeTutor.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {

    ProfileRepository profileRepository;
    UserRepository userRepository;

    public ResponseEntity<Object> decideOnProfile(String profileId, boolean isApproved) {
        JSONObject response = new JSONObject();

        // Find the profile first
        Profile profile = profileRepository.findProfileById(profileId);
        if(profile == null){
            response.put("status",false);
            response.put("message", "Profile does not exist!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        // Find whose profile that belongs to
        String userId = profile.getTutor().getId();
        if(userId.equals("") || userId == null){
            response.put("status",false);
            response.put("message", "User with this profile does not exist!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        // Find the user object to activate his/her profile
        User user = userRepository.findById(userId);
        String successMessage = "";
        if(user == null){
            response.put("status",false);
            response.put("message", "User with this id does not exist!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        // Activate / Deactivate the user based on the flag
        if(isApproved){
            successMessage = "approved";
            user.getRoles().get(0).setActive(true);
            profile.setProfileState(ProfileState.APPROVED);
        }else{
            successMessage = "blocked";
            user.getRoles().get(0).setActive(false);
            profile.setProfileState(ProfileState.BLOCKED);
        }
        // Save the user
        userRepository.save(user);
        profileRepository.save(profile);
        response.put("status",true);
        response.put("message", "Profile "+successMessage+" successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Object> getAllTutors() {
        List<User> users = userRepository.findAll();
        List<CustomTutorResponse> tutors = new ArrayList<>();
        CustomTutorResponse customTutorResponse;
        for(User u: users){
            if(u.getRoles().get(0).getRoleName().equals("tutor")){
                customTutorResponse = new CustomTutorResponse();
                customTutorResponse.setUser(u);
                customTutorResponse.setProfile(getProfile(u));
                tutors.add(customTutorResponse);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(tutors);

    }

    public ResponseEntity<Object> getAllParents() {
        List<User> users = userRepository.findAll();
        List<User> parents = new ArrayList<>();
        for(User u: users){
            if(u.getRoles().get(0).getRoleName().equals("parent")){
                parents.add(u);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(parents);
    }

    public Profile getProfile(User tutor){
        Profile profile = profileRepository.findProfileByTutorId(tutor.getId());
        if(profile == null){
            return null;
        }else{
            return profile;
        }
    }
}
