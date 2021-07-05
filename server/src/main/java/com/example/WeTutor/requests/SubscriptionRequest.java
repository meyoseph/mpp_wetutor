package com.example.WeTutor.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionRequest {
    private String cardNumber;
    private int expirationMonth;
    private int expirationYear;
    private String cvc;
    private String userEmail;
}

