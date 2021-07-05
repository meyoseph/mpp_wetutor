package com.example.WeTutor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Feedback_TBL")
public class Feedback{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id", unique = true, nullable = false)
    private String id;
    private int rating;
    private LocalDate feedbackDate;


    @OneToOne(mappedBy = "parentFeedback")
    private Parent parent;

    @OneToOne(mappedBy = "tutorFeedback")
    private Tutor tutor;


    public Feedback(Parent parent, int rating, Tutor tutor){
        this.parent = parent;
        this.tutor = tutor;
        this.rating = rating;
        this.feedbackDate = LocalDate.now();
    }


}