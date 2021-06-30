package com.example.WeTutor.requests;

import com.example.WeTutor.entities.Profile;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TutorRequest {
    private final String educationLevel;
    private final Profile profile;
}