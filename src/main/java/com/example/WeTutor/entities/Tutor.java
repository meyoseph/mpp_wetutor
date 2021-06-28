package com.example.WeTutor.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Tutor_TBL")
public class Tutor extends Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tutor_id", unique = true, nullable = false)
    private String tutorId;
    private String educationLevel;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name="profile_id")
    private Profile profile;

    public Tutor(){
        this.roleName = "tutor";
    }

    public Tutor(String id, String roleName, List<User> users, String educationLevel, Profile profile) {
        super(id, roleName, users);
        this.educationLevel = educationLevel;
        this.profile = profile;
    }

    public Tutor(String educationLevel, Profile profile) {
        this.educationLevel = educationLevel;
        this.profile = profile;
    }
}
