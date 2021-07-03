package com.example.WeTutor.services;

import com.example.WeTutor.entities.Location;
import com.example.WeTutor.entities.Profile;
import com.example.WeTutor.repositories.LocationRepository;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {
   LocationRepository locationRepository;

    public ResponseEntity<Object> getAllLocations() {
        JSONObject responseObject = new JSONObject();

        List<Location> locations = locationRepository.findAll();
        if(locations != null){
            responseObject.put("success",true);
            responseObject.put("locations", locations);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }else{
            responseObject.put("error",true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

    }

    public ResponseEntity<Object> getLocation(String locationName) {
        JSONObject responseObject = new JSONObject();

        Location location = locationRepository.findLocationByName(locationName);

        responseObject.put("success",true);
        responseObject.put("location", location);

        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

    public ResponseEntity<Object> deleteLocations(String name) {
        JSONObject responseObject = new JSONObject();

        boolean flag = false;

        Location location = locationRepository.findLocationByName(name);
        if(location == null) {
            responseObject.put("location", "location doesn't exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

        if(responseObject.isEmpty()){
            locationRepository.deleteLocationByName(name);
            responseObject.put("success", true);
            responseObject.put("message","Location deleted successfully");

            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

    }

}
