package com.example.WeTutor.requests;

import com.example.WeTutor.entities.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FeedbackRequest {
    private final String tutorEmail;
    private final String parentEmail;
    private final int rating;
}