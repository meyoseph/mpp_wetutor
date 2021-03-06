package com.example.WeTutor.controllers;

import com.example.WeTutor.requests.AuthRequest;
import com.example.WeTutor.requests.RegistrationRequest;
import com.example.WeTutor.services.LoginService;
import com.example.WeTutor.services.RegistrationService;
import com.example.WeTutor.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    @Autowired
    private UserService userService;

    @GetMapping("/test")
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
    @GetMapping(path = "/current")
    public ResponseEntity<Object> loggedInUser() {
        ResponseEntity<Object> user = loginService.loggedInUser();
        return user;
    }


}
