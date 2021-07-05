package com.example.WeTutor.DTO;

import com.example.WeTutor.entities.ProfileState;
import com.example.WeTutor.entities.User;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProfileDto {
    private String id;
    private String age;
    private String firstName;
    private String lastName;
    private String gender;
    private String majorSubject;
    private String subjects;
    private String educations;
    private String workExperiences;
    private String languages;
    private String profilePic;
    @Enumerated(EnumType.STRING)
    private ProfileState profileState;
    private int rating;
    private int ratedBy;
    private User tutor;
}