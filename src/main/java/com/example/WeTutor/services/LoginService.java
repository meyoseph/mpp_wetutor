package com.example.WeTutor.services;

import com.example.WeTutor.entities.User;
import com.example.WeTutor.repositories.UserRepository;
import com.example.WeTutor.requests.AuthRequest;
import com.example.WeTutor.requests.LoggedInUserRequest;
import com.example.WeTutor.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    public ResponseEntity<Object> authenticate(AuthRequest request) throws Throwable {
        JSONObject responseObject = new JSONObject();
        if(!validateInputs(request.getEmail())){
            responseObject.put("email","Email is required");
        }
        if(!validateInputs(request.getPassword())){
            responseObject.put("password","Password is required");
        }
        if(!responseObject.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }else{
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
                );
            } catch (Exception ex) {
                responseObject.put("credentials","Invalid credentials");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseObject);
            }
            String token = jwtUtil.generateToken(request.getEmail());
            responseObject.put("success",true);
            responseObject.put("token","Bearer " +token);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }

    }

    public User loggedInUser(LoggedInUserRequest request) {
        Optional<User> optionalUser = repository.findByEmail(request.getEmail());
        User user = optionalUser.get();
        return user;
    }

    public boolean validateInputs(String input){
        if(input == null){
            return false;
        } else return true;
    }
}
