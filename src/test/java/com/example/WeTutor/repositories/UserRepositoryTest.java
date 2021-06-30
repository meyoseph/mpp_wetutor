package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.Role;
import com.example.WeTutor.entities.Tutor;
import com.example.WeTutor.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void itShouldFindUserByEmail(){
        Role role = new Tutor();
        String email = "test@gmail.com";
        User user = new User("test_username", "test_password", email, role);
        userRepository.save(user);
        User user2 = userRepository.findByEmail(email);
        assertThat(user2).isEqualTo(user);
    }
}
