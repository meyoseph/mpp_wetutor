package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.Role;
import com.example.WeTutor.entities.Tutor;
import com.example.WeTutor.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class UserRepositoryTest {
    private Role role;
    private String email;
    private String password;
    private String userName;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void SetUp(){
        role = new Tutor();
        email = "test@gmail.com";
        password = "test_password";
        userName = "test";
    }

    @Test
    void itShouldFindUserByEmail(){
        String email2 = "test123@gmail.com";
        User user = new User(userName, password, email2, role);
        userRepository.save(user);
        User user2 = userRepository.findByEmail(email2);
        assertThat(user2).isEqualTo(user);
    }

    @Test
    void itShouldFindUserByUserName(){
        String email2 = "test2@gmail.com";
        String userName2 = "jos";
        User user = new User(userName2, password, email2, role);
        userRepository.save(user);
        User user2 = userRepository.findByUserName(userName2);
        assertThat(user2).isEqualTo(user);
    }

    @Test
    void itShouldFindById(){
        User user = new User(userName, password, email, role);
        userRepository.save(user);
        User user2 = userRepository.findById(user.getId());
        assertThat(user2).isEqualTo(user);
    }
}
