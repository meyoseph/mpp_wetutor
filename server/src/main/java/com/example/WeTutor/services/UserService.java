package com.example.WeTutor.services;

import com.example.WeTutor.entities.*;
import com.example.WeTutor.repositories.ParentRepository;
import com.example.WeTutor.repositories.RoleRepository;
import com.example.WeTutor.repositories.TutorRepository;
import com.example.WeTutor.repositories.UserRepository;
import com.example.WeTutor.requests.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository repository;
    private RoleRepository roleRepository;
    private TutorRepository tutorRepository;
    private ParentRepository parentRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }

    public ResponseEntity<Object> signUpUser(RegistrationRequest request){
        User emailExists = repository.findByEmail(request.getEmail());
        JSONObject errorObject = new JSONObject();
        if(emailExists != null){
            errorObject.put("email", "Email already taken");
        }
        if(!validateInputs(request.getUserName())){
            errorObject.put("username", "Username is required");
        }
        if(!validateInputs(request.getEmail())){
            errorObject.put("email", "Email is required");
        }
        if(validateEmail(request.getEmail()) != true){
            errorObject.put("email", "Email is not valid!");
        }
        if(!validateInputs(request.getPassword())){
            errorObject.put("password", "Password is required");
        }
        if(!validateInputs(request.getPassword2())){
            errorObject.put("password2", "Password confirmation is required");
        }
        if(!request.getPassword().equals(request.getPassword2())){
            errorObject.put("password2", "Password mismatch");
        }
        if(!validateInputs(request.getRole())){
            errorObject.put("role", "Role is required");
        }
        if(errorObject.isEmpty()){
            Role role;
            // Code to be modified later
            if(request.getRole().equals("parent")){
                role = new Parent(request.getEmail());
                Parent parent = new Parent(request.getEmail());
                parentRepository.save(parent);
            }else{
                role = new Tutor(request.getEmail());
                Tutor tutor = new Tutor(request.getEmail());
                tutorRepository.save(tutor);
            }
            List<Role> roles = roleRepository.findAll();
            boolean roleExists = false;
            for(Role r: roles){
                if(r.getRoleName().equals(role.getRoleName())){
                    roleExists = true;
                    role = r;
                    break;
                }
            }

            if(!roleExists){
                roleRepository.save(role);
            }

            User user = new User(request.getUserName(), request.getPassword(), request.getEmail(), role);
            JSONObject successObject = new JSONObject();
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            repository.save(user);
            successObject.put("success",true);
            successObject.put("message","Registered Successfully");
            return ResponseEntity.status(HttpStatus.OK).body(successObject);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorObject);
        }
    }

    public boolean validateInputs(String input){
        if(input == null || input == ""){
            return false;
        } else return true;
    }

    public Boolean validateEmail(String string){
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = pattern.matcher(string);

        return matcher.matches();
    }
}
