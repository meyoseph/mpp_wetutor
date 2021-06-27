package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.Profile;
import com.example.WeTutor.entities.Tutor;
import com.example.WeTutor.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends MongoRepository<Profile,Integer> {
    Profile findByTutorId(String tutorId);
    Profile findByProfileId(String profileId);
    Profile getProfileByTutor(Tutor tutor);
}
