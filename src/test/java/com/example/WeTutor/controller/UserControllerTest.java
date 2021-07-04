package com.example.WeTutor.controller;

import com.example.WeTutor.controllers.UserController;
import com.example.WeTutor.entities.Role;
import com.example.WeTutor.entities.Tutor;
import com.example.WeTutor.entities.User;
import com.example.WeTutor.requests.AuthRequest;
import com.example.WeTutor.requests.RegistrationRequest;
import com.example.WeTutor.services.LoginService;
import com.example.WeTutor.services.RegistrationService;
import com.example.WeTutor.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Objects;

import static org.assertj.core.api.Fail.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService registrationService;

    @MockBean
    private LoginService loginService;

    @MockBean
    private UserService userService;

    @Test
    void itShouldLoginSuccessfully() throws Exception {
        AuthRequest authRequest = new AuthRequest("test@gmail.com", "1234567");
        ResultActions resultActions = mockMvc.perform(post("/api/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(authRequest))));
        resultActions.andExpect(status().isOk());
    }

    @Test
    void itShouldRegisterUser() throws Exception{
        RegistrationRequest registrationRequest = new RegistrationRequest(
                "test",
                "email@gmail.com",
                "password",
                "password",
                "tutor");
        ResultActions resultActions = mockMvc.perform(post("/api/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(registrationRequest))));
        resultActions.andExpect(status().isOk());
    }

    private String objectToJson(Object object){
        try{
            return new ObjectMapper().writeValueAsString(object);
        }catch (JsonProcessingException e){
            fail("failed to convert object to json");
            return null;
        }
    }
}
