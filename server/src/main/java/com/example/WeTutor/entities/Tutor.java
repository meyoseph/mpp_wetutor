package com.example.WeTutor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TUTOR_TBL")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Tutor extends Role{

    private String email;


    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name="feedback_id", referencedColumnName = "feedback_id")
    @JsonBackReference
    private Feedback tutorFeedback;

    public Tutor(String email){
        this.roleName = "tutor";
        this.email = email;
        isActive = false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
