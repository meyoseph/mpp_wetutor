package com.example.WeTutor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
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

    @OneToOne(mappedBy = "parentFeedbacks")
    private User parent;

    @OneToOne(mappedBy = "tutorFeedbacks")
    private User tutor;

    public Feedback(User parent, int rating, User tutor){
        this.parent = parent;
        this.tutor = tutor;
        this.rating = rating;
        this.feedbackDate = LocalDate.now();
    }

}