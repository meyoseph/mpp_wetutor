package com.example.WeTutor.requests;

import com.example.WeTutor.entities.Tutor;
import lombok.*;


@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProfileRequest {
    private final String age;
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String phoneNumber;
    private final String location;
    private final String motive;
    private final String majorSubject;
    private final String[] subjects;
    private final String educations;
    private final String workExperiences;
    private final String[] languages;
    private final String tutor;
}