package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.Tutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends MongoRepository<Tutor,Integer> {
    Tutor findTutorById(String tutorId);
}
