package com.example.WeTutor.CustomResponses;

import com.example.WeTutor.entities.Profile;
import com.example.WeTutor.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomTutorResponse {
    private User user;
    private Profile profile;
}
