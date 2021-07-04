package com.example.WeTutor.controller;

import com.example.WeTutor.services.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    @Test
    void itShouldHitApproveProfilesEndPointSuccessfully() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/admin/approve-profile/1"));
        resultActions.andExpect(status().isOk());
    }

    @Test
    void itShouldHitDeactivateProfilesEndPointSuccessfully() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/admin/block-profile/1"));
        resultActions.andExpect(status().isOk());
    }

    @Test
    void itShouldHitGetAllTutorProfilesEndPointSuccessfully() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/admin/all-tutors"));
        resultActions.andExpect(status().isOk());
    }

    @Test
    void itShouldHitGetAllParentProfilesEndPointSuccessfully() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/admin/all-parents"));
        resultActions.andExpect(status().isOk());
    }
}
