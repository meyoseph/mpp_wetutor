package com.example.WeTutor.services;


import com.example.WeTutor.entities.Feedback;
import com.example.WeTutor.entities.Tutor;
import com.example.WeTutor.repositories.*;
import com.example.WeTutor.requests.SubscriptionRequest;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TutorServiceTest {
    @Mock
    TutorRepository tutorRepository;
    @Mock
    ParentRepository parentRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    FeedbackRepository feedbackRepository;
    @Mock
    ProfileRepository profileRepository;

    @Autowired
    private TutorService tutorService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        tutorService = new TutorService(tutorRepository, parentRepository, userRepository, feedbackRepository, profileRepository);
    }

    @Test
    void itShoudGetAllTutors(){
        Tutor tutor = new Tutor("test", new Feedback());
        Tutor tutor1 = new Tutor("testtest", new Feedback());
        List<Tutor> tutors = new ArrayList<>();
        tutors.add(tutor);
        tutors.add(tutor1);
        when(tutorRepository.findAll()).thenReturn(tutors);
        ResponseEntity<Object> responseEntity = tutorService.getAllTutors();
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void itShouldGetTutor(){
        Tutor tutor = new Tutor("test", new Feedback());
        when(tutorRepository.findTutorById(tutor.getId())).thenReturn(tutor);
        ResponseEntity<Object> responseEntity = tutorService.getTutor(tutor.getId());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}
