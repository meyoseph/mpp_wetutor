package com.example.WeTutor.entities;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "PROFILE_TBL")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id", unique = true, nullable = false)
    private String profileId;
    private String age;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String location;
    private String motive;
    private String majorSubject;
    private String subjects;//Type
    private String educations;
    private String workExperiences;
    private String languages;

    @Enumerated(EnumType.STRING)
    private ProfileState profileState;

    @OneToOne(mappedBy = "profile")
    private Tutor tutor;

    public Profile(String age, String firstName, String lastName, String gender, String phoneNumber, String location, String motive, String majorSubject, String subjects, String educations, String workExperiences, String languages, Tutor tutor) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.motive = motive;
        this.majorSubject = majorSubject;
        this.subjects = subjects;
        this.educations = educations;
        this.workExperiences = workExperiences;
        this.languages = languages;
        this.profileState = ProfileState.BEGINNING;
        this.tutor = tutor;
    }

}
