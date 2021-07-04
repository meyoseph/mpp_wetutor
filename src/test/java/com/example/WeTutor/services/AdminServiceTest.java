package com.example.WeTutor.services;

import com.example.WeTutor.entities.Parent;
import com.example.WeTutor.entities.Role;
import com.example.WeTutor.entities.User;
import com.example.WeTutor.repositories.ProfileRepository;
import com.example.WeTutor.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.any;
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

//    public ResponseEntity<Object> getAllParents() {
//        List<User> users = userRepository.findAll();
//        List<User> parents = new ArrayList<>();
//        for(User u: users){
//            if(u.getRoles().get(0).getRoleName().equals("parent")){
//                parents.add(u);
//            }
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(parents);
//    }
}
