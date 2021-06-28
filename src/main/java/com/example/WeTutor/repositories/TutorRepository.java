package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.Profile;
import com.example.WeTutor.entities.Tutor;
import com.example.WeTutor.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TutorRepository extends MongoRepository<Tutor,Integer> {
    Optional<Tutor> findByTutorId(String tutorId);
}
