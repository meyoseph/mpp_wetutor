package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.Profile;
import com.example.WeTutor.entities.Role;
import com.example.WeTutor.entities.Tutor;
import com.example.WeTutor.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
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

    @Test
    void itShouldFindProfileByTutorId(){
        User user = new User(userName, password, email, role);
        userRepository.save(user);
        User user2 = userRepository.findById(user.getId());
        Profile profile = new Profile(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+14707383195",
                "iowa",
                "test",
                "math",
                "java, asd, sa, ea",
                "msc",
                "4 years",
                "test",
                "test",
                user2
        );
        profileRepository.save(profile);
        Profile testProfile = profileRepository.findProfileByTutorId(user2.getId());
        assertThat(testProfile).isEqualTo(profile);
    }

    @Test
    void itShouldFindProfileById(){
        String email2 = "test2@gmail.com";
        String userName2 = "jos";
        User user = new User(userName2, password, email2, role);
        userRepository.save(user);
        User user2 = userRepository.findByUserName(userName2);
        Profile profile = new Profile(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+14707383195",
                "iowa",
                "test",
                "math",
                "java, asd, sa, ea",
                "msc",
                "4 years",
                "test",
                "test",
                user2
        );
        profileRepository.save(profile);
        Profile testProfile = profileRepository.findProfileById(profile.getId());
        assertThat(testProfile).isEqualTo(profile);
    }
}
