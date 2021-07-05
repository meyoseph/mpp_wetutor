package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.Profile;
import com.example.WeTutor.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends MongoRepository<Profile,Integer> {
    Profile findProfileByTutorId(String tutorId);
    Profile findProfileById(String profileId);
    void deleteProfileById(String profileId);
}
