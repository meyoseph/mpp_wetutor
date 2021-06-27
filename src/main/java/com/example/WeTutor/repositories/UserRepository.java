package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,Integer> {
    Optional<User> findByUserName(String username);
    Optional<User> findByEmail(String email);
}