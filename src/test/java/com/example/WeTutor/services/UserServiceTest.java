package com.example.WeTutor.services;

import com.example.WeTutor.entities.User;
import com.example.WeTutor.repositories.ParentRepository;
import com.example.WeTutor.repositories.RoleRepository;
import com.example.WeTutor.repositories.TutorRepository;
import com.example.WeTutor.repositories.UserRepository;
import com.example.WeTutor.requests.RegistrationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.assertj.core.api.Assertions.assertThat;

public class UserServiceTest {
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private ParentRepository parentRepository;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;
    private RegistrationRequest request;

    @BeforeEach
    void setUp(){
        request = new RegistrationRequest("meyoseph",
                "meyoseph@gmail.com",
                "password",
                "password",
                "role");
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository, roleRepository, tutorRepository, parentRepository, new BCryptPasswordEncoder());
    }

    @Test
    void itShouldSignUpUser(){
        given(userRepository.findByUserName(request.getUserName())).willReturn(null);
        userService.signUpUser(request);
        then(userRepository).should().save(userArgumentCaptor.capture());
        User userArgumentCaptorValue = userArgumentCaptor.getValue();
        Assert.notNull(userArgumentCaptorValue);
    }

    @Test
    void itShouldValidateEmptyString(){
        boolean bad = userService.validateInputs("");
        assertThat(bad).isEqualTo(false);
    }

    @Test
    void itShouldValidateNullValue(){
        boolean bad = userService.validateInputs(null);
        assertThat(bad).isEqualTo(false);
    }

    @Test
    void itShouldValidateInput(){
        boolean good = userService.validateInputs("meyoseph");
        assertThat(good).isEqualTo(true);
    }
}
