package com.example.WeTutor.requests;

import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoggedInUserRequest {
    private String email;
}
