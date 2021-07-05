package com.example.WeTutor.services;

import com.example.WeTutor.entities.*;
import com.example.WeTutor.repositories.ProfileRepository;
import com.example.WeTutor.repositories.UserRepository;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AdminServiceTest {
    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private UserRepository userRepository;

    @Autowired
    private AdminService adminService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        adminService = new AdminService(profileRepository, userRepository);
    }

    @Test
    void itShoudGetAllParents(){
        Role role = new Parent();
        User user1 = new User("user", "password", "email", role);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
        User user2 = new User("test", "test", "test@gmail.com", role);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user2);
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);
        ResponseEntity<Object> responseEntity = adminService.getAllParents();
        System.out.println(user2.getRoles().get(0).getRoleName());
    }

    @Test
    void itShouldGetProfile(){
        Role role = new Tutor();
        User user = new User("test1", "test2", "test@gmail.com", role);
        Profile profile = new Profile(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                "java, ruby",
                "degree",
                "test",
                "test",
                "test",
                user);
        when(profileRepository.findProfileByTutorId(user.getId())).thenReturn(profile);
        Profile profile1 = adminService.getProfile(user);
        assertThat(profile1).isEqualTo(profile1);
    }

    @Test
    void itShouldReturnProfileDoesNotExist(){
        when(profileRepository.findProfileById("123")).thenReturn(null);
        ResponseEntity<Object> responseEntity = adminService.decideOnProfile("123", false);
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    void itShouldFailWhenUserIdDidntExist(){
        Role role = new Tutor();
        User user = new User("test1", "test2", "test@gmail.com", role);
        Profile profile = new Profile(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                "java, ruby",
                "degree",
                "test",
                "test",
                "test",
                user);
        when(profileRepository.findProfileById(user.getId())).thenReturn(profile);
        profile.getTutor().setId("");
        ResponseEntity<Object> responseEntity = adminService.decideOnProfile(profile.getId(), false);
        assertEquals(400, responseEntity.getStatusCodeValue());

    }

    @Test
    void itShouldFailWhenUserIsNull(){
        Role role = new Tutor();
        User user = new User("test1", "test2", "test@gmail.com", role);
        user.setId("21");
        Profile profile = new Profile(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                "java, ruby",
                "degree",
                "test",
                "test",
                "test",
                user);
        when(profileRepository.findProfileById(profile.getId())).thenReturn(profile);
        when(userRepository.findById(user.getId())).thenReturn(null);
        ResponseEntity<Object> responseEntity = adminService.decideOnProfile(profile.getId(), false);
        assertEquals(400, responseEntity.getStatusCodeValue());
        System.out.println(responseEntity.getBody());
    }
}
