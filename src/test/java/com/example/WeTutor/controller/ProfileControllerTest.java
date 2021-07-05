package com.example.WeTutor.controller;

import com.example.WeTutor.controllers.ProfileController;
import com.example.WeTutor.requests.AuthRequest;
import com.example.WeTutor.requests.ProfileRequest;
import com.example.WeTutor.requests.RegistrationRequest;
import com.example.WeTutor.services.ProfileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Objects;

import static org.assertj.core.api.Fail.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ProfileController.class)
@AutoConfigureMockMvc(addFilters = false)
@EnableWebMvc
public class ProfileControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileService profileService;

    @Test
    void itShouldHitGetALLProfilesEndPointSuccessfully() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/profile/all"));
        resultActions.andExpect(status().isOk());
    }

    @Test
    void itShouldHitGetProfilesEndPointSuccessfully() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/profile/tutors"));
        resultActions.andExpect(status().isOk());
    }

    @Test
    void itShouldHitGetProfileEndPointSuccessfully() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/profile/current"));
        resultActions.andExpect(status().isOk());
    }

    @Test
    void itShouldHitGetProfileByIdEndPointSuccessfully() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/profile/1"));
        resultActions.andExpect(status().isOk());
    }

    @Disabled
    @Test
    void itShouldHitCreateProfileEndPointSuccessfully() throws Exception {
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"test"};
        ProfileRequest profileRequest = new ProfileRequest(
                "24",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "test",
                "test",
                "math",
                subjects,
                "test",
                "",
                languages,
                "1",
                "1");
        ResultActions resultActions = mockMvc.perform(post("/api/profile/create-profile")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(profileRequest))));
        resultActions.andExpect(status().isOk());
    }

    @Disabled
    @Test
    void itShouldHitUpdateProfileEndPointSuccessfully() throws Exception {
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"test"};
        ProfileRequest profileRequest = new ProfileRequest(
                "24",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "test",
                "test",
                "math",
                subjects,
                "test",
                "",
                languages,
                "1",
                "1");

        ResultActions resultActions = mockMvc.perform(post("/api/profile/update-profile")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(profileRequest))));
        resultActions.andExpect(status().isOk());
    }

    @Test
    void itShouldHitDeleteProfileByIdEndPointSuccessfully() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/profile/delete-profile-by-tutor-id/").param("tutor_id", "1"));
        resultActions.andExpect(status().isOk());
    }

    @Test
    void itShouldHitDeleteProfileByProfileIdEndPointSuccessfully() throws Exception {
        ResultActions resultActions = mockMvc.perform(delete("/api/profile/1"));
        resultActions.andExpect(status().isOk());
    }

    @Test
    void itShouldHitRequestApprovalEndPointSuccessfully() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/profile/request-approval/1"));
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
