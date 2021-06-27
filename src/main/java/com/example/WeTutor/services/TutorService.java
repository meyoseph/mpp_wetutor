package com.example.WeTutor.services;

import com.example.WeTutor.entities.Profile;
import com.example.WeTutor.entities.Tutor;
import com.example.WeTutor.repositories.ProfileRepository;
import com.example.WeTutor.repositories.TutorRepository;
import com.example.WeTutor.requests.ProfileRequest;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TutorService {

    TutorRepository tutorRepository;

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

        Tutor tutor = tutorRepository.findByTutorId(tutorId);

        responseObject.put("success",true);
        responseObject.put("tutor", tutor);

        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

    public boolean validateInputs(String input){
        if(input == null || input == ""){
            return false;
        } else return true;
    }

}
