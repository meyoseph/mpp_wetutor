package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.Profile;
import com.example.WeTutor.entities.Role;
import com.example.WeTutor.entities.Tutor;
import com.example.WeTutor.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class TutorRepositoryTest {
    @Autowired
    private TutorRepository underTest;

    @Test
    void itShouldFindTutorById() {
        Tutor tutor = new Tutor("test", new Profile());
        underTest.save(tutor);
        Optional<Tutor> optionalTutor = underTest.findByTutorId(tutor.getTutorId());
        assertThat(optionalTutor).isPresent().hasValueSatisfying(
                t -> { assertThat(t).isEqualTo(tutor); });
    }
}
