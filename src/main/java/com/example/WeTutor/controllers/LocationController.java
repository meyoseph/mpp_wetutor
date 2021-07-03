package com.example.WeTutor.controllers;

import com.example.WeTutor.services.LocationService;
import com.example.WeTutor.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/locations")
    public ResponseEntity<Object> getProfiles() throws Throwable {
        ResponseEntity<Object> response = locationService.getAllLocations();
        return response;
    }
    // Get tutor profile by location  name
    @GetMapping("/{locationName}")
    public ResponseEntity<Object> getTutor(@RequestParam String locationName) throws Exception {
        ResponseEntity<Object> response = locationService.getLocation(locationName);
        return response;
    }

    // Delete profile in DB
    @GetMapping("/delete-location-by-tutor-location")
    public ResponseEntity<Object> deleteByLocationName(@RequestParam String location_name) {
        ResponseEntity<Object> response = locationService.deleteLocations(location_name);
        return response;
    }
}
