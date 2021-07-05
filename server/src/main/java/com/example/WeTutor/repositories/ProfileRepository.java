package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends MongoRepository<Profile,Integer> {
    Profile findProfileByTutorId(String tutorId);
    Profile findProfileById(String profileId);
    void deleteProfileById(String profileId);

    List<Profile> getProfileByLocation(String tutorLocation);
    List<Profile> getProfilesByRating(int rating);
    List<Profile> getProfileByMajorSubject(String tutorSubjects);
    List<Profile> getProfileByGender(String tutorGender);
}
