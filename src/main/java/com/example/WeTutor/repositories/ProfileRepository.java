package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.Profile;
import com.example.WeTutor.entities.Tutor;
import com.example.WeTutor.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends MongoRepository<Profile,Integer> {
    Optional<Profile> findByTutorId(String tutorId);
    Optional<Profile> findByProfileId(String profileId);
    Optional<Profile> getProfileByTutor(Tutor tutor);
}
