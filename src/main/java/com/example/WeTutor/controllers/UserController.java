package com.example.WeTutor.controllers;

import com.example.WeTutor.entities.User;
import com.example.WeTutor.requests.AuthRequest;
import com.example.WeTutor.requests.LoggedInUserRequest;
import com.example.WeTutor.requests.RegistrationRequest;
import com.example.WeTutor.services.LoginService;
import com.example.WeTutor.services.RegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api")
public class UserController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to WeTutor Web Application !!";
    }

    // Authenticate user by email and password , does the same thing as login
    @PostMapping("/authenticate")
    public ResponseEntity<Object> login(@RequestBody AuthRequest authRequest) throws Throwable {
        ResponseEntity<Object> response = loginService.authenticate(authRequest);
        return response;

    }

    // Registers or Adds user to DB
    @PostMapping("/registration")
    public ResponseEntity<Object> register(@RequestBody RegistrationRequest request) throws JsonProcessingException {
        ResponseEntity<Object> response = registrationService.register(request);
        return response;
    }

    // Retrieves the current logged in user
    @PostMapping(path = "/current")
    public User loggedInUser(@RequestBody LoggedInUserRequest request) throws Exception {
        User user = loginService.loggedInUser(request);
        return user;

    }

}
