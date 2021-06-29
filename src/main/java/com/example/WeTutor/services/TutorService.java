package com.example.WeTutor.services;

import com.example.WeTutor.entities.*;
import com.example.WeTutor.repositories.FeedbackRepository;
import com.example.WeTutor.repositories.RoleRepository;
import com.example.WeTutor.repositories.TutorRepository;
import com.example.WeTutor.repositories.UserRepository;
import com.example.WeTutor.requests.FeedbackRequest;
import com.example.WeTutor.requests.ProfileRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class TutorService {

    TutorRepository tutorRepository;
    FeedbackRepository feedbackRepository;
    UserRepository userRepository;

    public ResponseEntity<Object> getAllTutors() {
        JSONObject responseObject = new JSONObject();

        List<Tutor> tutors = tutorRepository.findAll();
        if(tutors != null){
            responseObject.put("success",true);
            responseObject.put("tutors", tutors);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }else{
            responseObject.put("error",true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

    }

    public ResponseEntity<Object> getTutor(String tutorId) {
        JSONObject responseObject = new JSONObject();

        Tutor tutor = tutorRepository.findTutorById(tutorId);

        responseObject.put("success",true);
        responseObject.put("tutor", tutor);

        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

    public ResponseEntity<Object> rateTutor(FeedbackRequest feedbackRequest) {

        JSONObject responseObject = new JSONObject();

        if(!validateInputs(feedbackRequest.getRating()+""))
            responseObject.put("rating", "Rating is required");

        User tutor = userRepository.findByEmail(feedbackRequest.getTutor());
        User parent = userRepository.findByEmail(feedbackRequest.getParent());

        Feedback feedback = new Feedback(parent, feedbackRequest.getRating(), tutor);

        feedbackRepository.save(feedback);
        responseObject.put("success",true);
        responseObject.put("message","Rating created successfully");
        responseObject.put("message2", parent);
        responseObject.put("message3", tutor);

        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

    public boolean validateInputs(String input){
        if(input == null || input == ""){
            return false;
        } else return true;
    }

}
