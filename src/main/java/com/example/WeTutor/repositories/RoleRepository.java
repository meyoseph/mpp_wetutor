package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role,Integer> {
}
