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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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

    public String getLoggedInUserEmail(){
        String username;
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        email = repository.findByUserName(username).getEmail();
        return email;
    }

    public ResponseEntity<Object> loggedInUser() {
        JSONObject response = new JSONObject();
        User user = repository.findByEmail(getLoggedInUserEmail());
        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }else{
            response.put("success",false);
            response.put("message", "No user found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public boolean validateInputs(String input){
        if(input == null || input == ""){
            return false;
        } else return true;
    }
}
