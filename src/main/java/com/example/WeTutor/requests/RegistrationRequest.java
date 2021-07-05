package com.example.WeTutor.requests;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String userName;
    private final String email;
    private final String password;
    private final String password2;
    private final String role;
}