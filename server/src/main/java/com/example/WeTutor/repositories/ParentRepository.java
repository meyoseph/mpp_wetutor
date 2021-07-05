package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.Parent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends MongoRepository<Parent,Integer> {
    Parent findParentById(String parentId);
    Parent findParentByEmail(String email);
}