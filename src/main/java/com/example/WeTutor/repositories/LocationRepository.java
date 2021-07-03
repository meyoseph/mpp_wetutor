package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.Location;
import com.example.WeTutor.entities.Tutor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location,String> {
    Location findLocationByName(String name);
    //Location findLocationByLocationId(String location_id);
    void deleteLocationByName(String name);
}
