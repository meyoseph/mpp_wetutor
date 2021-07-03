package com.example.WeTutor.services;

import com.example.WeTutor.repositories.ProfileRepository;
import com.example.WeTutor.repositories.TutorRepository;
import com.example.WeTutor.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

public class ProfileServiceTest {
    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Mock
    private ProfileService profileService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        profileService = new ProfileService(profileRepository, tutorRepository, userRepository, modelMapper);
    }

    @Test
    void itShouldReturnListOfProfiles(){
    }
}
