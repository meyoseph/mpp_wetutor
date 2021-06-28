package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.Profile;
import com.example.WeTutor.entities.Tutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class ProfileRepositoryTest {

    private Profile profile;
    @Autowired
    private ProfileRepository underTest;

    @BeforeEach
    void setUp() {
        profile = new Profile(
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
                new Tutor()
        );
    }

    @Test
    void itShouldFindTutorById() {
        underTest.save(profile);
        Optional<Profile> optionalProfile = underTest.findByTutorId(profile.getTutor().getTutorId());
        assertThat(optionalProfile).isPresent().hasValueSatisfying(
                p -> { assertThat(p).isEqualTo(profile); });
    }

    @Test
    void itShouldFindByProfileId(){
        underTest.save(profile);
        Optional<Profile> optionalProfile = underTest.findByTutorId(profile.getProfileId());
        assertThat(optionalProfile).isPresent().hasValueSatisfying(
                p -> { assertThat(p).isEqualTo(profile); });
    }

    @Test
    void itShouldGetProfileByTutor(){
        underTest.save(profile);
        Optional<Profile> optionalProfile = underTest.getProfileByTutor(profile.getTutor());
        assertThat(optionalProfile).isPresent().hasValueSatisfying(
                p -> { assertThat(p).isEqualTo(profile); });
    }
}
