package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.Parent;
import com.example.WeTutor.entities.Role;
import com.example.WeTutor.entities.Tutor;
import com.example.WeTutor.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
//@AutoConfigureDataMongo
public class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    void itShouldFindUserByEmail() {
        Role role = new Tutor();
        String email = "test@gmail.com";
        User user = new User("test_username", "test_password", email, role);
        underTest.save(user);
        Optional<User> optionalUser = underTest.findByEmail(email);
        assertThat(optionalUser).isPresent().hasValueSatisfying(
                u -> { assertThat(u).isEqualTo(user); });
    }

    @Test
    void itShouldFindUserByUserName() {
        Role role = new Parent();
        String userName = "username";
        User user = new User(userName, "test_password2", "test@gmail.com", role);
        underTest.save(user);
        Optional<User> optionalUser = underTest.findByUserName(userName);
        assertThat(optionalUser).isPresent().hasValueSatisfying(
                u -> { assertThat(u).isEqualTo(user); });
    }
}