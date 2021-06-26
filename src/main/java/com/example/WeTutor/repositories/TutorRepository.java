package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.Profile;
import com.example.WeTutor.entities.Tutor;
import com.example.WeTutor.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends MongoRepository<Tutor,Integer> {
    Tutor findByTutorId(String tutorId);
}
