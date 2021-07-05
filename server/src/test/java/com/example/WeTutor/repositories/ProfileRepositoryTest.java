package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class ProfileRepositoryTest {
    private Role role;
    private String email;
    private String password;
    private String userName;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void SetUp(){
        role = new Tutor();
        email = "test@gmail.com";
        password = "test_password";
        userName = "test";
    }
}
