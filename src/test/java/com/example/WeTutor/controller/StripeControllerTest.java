package com.example.WeTutor.controller;

import com.example.WeTutor.requests.SubscriptionRequest;
import com.example.WeTutor.services.StripeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Objects;

import static org.assertj.core.api.Fail.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class StripeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StripeService stripeService;

    @Test
    void itShouldHitCheckoutEndPointSuccessfully() throws Exception {
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest(
                "12333333",
                04,
                25,
                "123",
                "test@gmail.com"
        );
        ResultActions resultActions = mockMvc.perform(post("/api/checkout").contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(subscriptionRequest))));
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
