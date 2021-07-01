package com.example.WeTutor.services;

import com.example.WeTutor.entities.Role;
import com.example.WeTutor.entities.Tutor;
import com.example.WeTutor.entities.User;
import com.example.WeTutor.repositories.FeedbackRepository;
import com.example.WeTutor.repositories.TutorRepository;
import com.example.WeTutor.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TutorServiceTest {
    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private UserRepository userRepository;

    @Autowired
    private TutorService tutorService;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        tutorService = new TutorService(tutorRepository, userRepository, feedbackRepository);
    }

//    @Test
//    void itReturnListOfTutors(){
//        Role tutor = new Tutor();
//        User user = new User("test", "test", "test@gmail.com", tutor);
//        userRepository.save(user);
//        ResponseEntity<Object> users = tutorService.getAllTutors();
//        //assertThat();
//    }
}
