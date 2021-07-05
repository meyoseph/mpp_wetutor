package com.example.WeTutor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import javax.persistence.*;



import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "PROFILE_TBL")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id", unique = true, nullable = false)
    private String id;
    private String age;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String location;
    private String motive;
    private String majorSubject;
    private String subjects;
    private String educations;
    private String workExperiences;
    private String languages;
    private String profilePic;
    private int rating;
    private int ratedBy;

    @Enumerated(EnumType.STRING)
    private ProfileState profileState;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "profile")
    private User tutor;

    public Profile(String age, String firstName,
                   String lastName, String gender, String phoneNumber,
                   String location, String motive, String majorSubject,
                   String subjects, String educations, String workExperiences,
                   String languages, String profilePic, User tutor) {
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
        this.profilePic = profilePic;
        this.tutor = tutor;
        this.rating = 0;
        this.ratedBy = 0;
    }

}
