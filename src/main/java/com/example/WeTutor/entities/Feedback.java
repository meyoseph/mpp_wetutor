package com.example.WeTutor.entities;

import javax.persistence.*;
import java.time.LocalDate;

public class Feedback{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id", unique = true, nullable = false)
    private String id;
    private int rating;
    private LocalDate feedbackDate;

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name="parent_id")
    private Parent parent;

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name="tutor_id")
    private Tutor tutor;

    public Feedback(Parent parent, Tutor tutor, int rating, LocalDate localDate){
        this.parent = parent;
        this.tutor = tutor;
        this.rating = rating;
        this.feedbackDate = localDate;
    }

}
