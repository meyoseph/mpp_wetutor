package com.example.WeTutor.services;

import com.example.WeTutor.entities.*;
import com.example.WeTutor.requests.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private UserService appUserService;
    public ResponseEntity<Object> register(RegistrationRequest request) {
        ResponseEntity<Object> res = appUserService.signUpUser(request);
        return res;
    }
}
