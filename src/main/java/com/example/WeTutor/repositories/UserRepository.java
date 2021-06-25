package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,Integer> {
    User findByUserName(String username);
    User findByEmail(String email);
}
