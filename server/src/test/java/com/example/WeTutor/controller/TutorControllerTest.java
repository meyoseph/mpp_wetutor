package com.example.WeTutor.controller;

import com.example.WeTutor.controllers.TutorController;
import com.example.WeTutor.requests.AuthRequest;
import com.example.WeTutor.requests.FeedbackRequest;
import com.example.WeTutor.services.RegistrationService;
import com.example.WeTutor.services.TutorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Objects;

import static org.assertj.core.api.Fail.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TutorController.class)
@AutoConfigureMockMvc(addFilters = false)
@EnableWebMvc
public class TutorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService registrationService;

    @MockBean
    private TutorService tutorService;

    @Test
    void itShouldHitGellAllTutorsEndPointSuccessfully() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/tutor/all"));
        resultActions.andExpect(status().isOk());
    }

    @Test
    void itShouldHitGetTutorEndPointSuccessfully() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/tutor/").param("tutor_id", "1"));
        resultActions.andExpect(status().isOk());
    }

    @Disabled
    @Test
    void itShouldHitRateTutorEndPointSuccessfully() throws Exception {
        FeedbackRequest feedbackRequest = new FeedbackRequest(
                "testtutor@gmail.com",
        "testparent@gmail.com",
         5
        );
        ResultActions resultActions = mockMvc.perform(post("/api/tutor/rate-tutor")
                .contentType(MediaType.APPLICATION_JSON).content(Objects.requireNonNull(objectToJson(feedbackRequest))));
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
