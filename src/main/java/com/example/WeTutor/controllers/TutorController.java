package com.example.WeTutor.controllers;

import com.example.WeTutor.requests.FeedbackRequest;
import com.example.WeTutor.requests.TutorRequest;
import com.example.WeTutor.services.TutorService;
import com.example.WeTutor.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/tutor")
public class TutorController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private TutorService tutorService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllTutors() throws Throwable {
        ResponseEntity<Object> response = tutorService.getAllTutors();
        return response;
    }
    @GetMapping("/")
    public ResponseEntity<Object> getTutor(@RequestParam String tutor_id) throws Exception {
        ResponseEntity<Object> response = tutorService.getTutor(tutor_id);
        return response;
    }

    // Creates tutor
    @PostMapping("/rate-tutor")
    public ResponseEntity<Object> rateTutor(@RequestBody FeedbackRequest feedbackRequest) throws Exception {
        ResponseEntity<Object> response = tutorService.rateTutor(feedbackRequest);
        return response;
    }

/*

    // Creates tutor
    @PostMapping("/create-tutor")
    public ResponseEntity<Object> createTutor(@RequestBody TutorRequest tutorRequest) throws Exception {
        ResponseEntity<Object> response = tutorService.addTutor(tutorRequest);
        return response;
    }

    // Updates tutor in DB
    @PostMapping("/update-tutor")
    public ResponseEntity<Object> register(@RequestBody TutorRequest tutorRequest) throws Exception {
        ResponseEntity<Object> response = tutorService.updateTutor(tutorRequest);
        return response;
    }

    // Delete tutor in DB
    @GetMapping("/delete-tutor-by-tutor-id")
    public ResponseEntity<Object> deleteByTutorId(@RequestParam String tutor_id) throws Exception {
        ResponseEntity<Object> response = tutorService.deleteTutorByTutorId(tutor_id);
        return response;
    }

    // Delete tutor in DB
    @GetMapping("/delete-tutor")
    public ResponseEntity<Object> deleteTutor(@RequestParam String tutor_id) throws Exception {
        ResponseEntity<Object> response = tutorService.deleteTutor(tutor_id);
        return response;
    }*/
}