package com.example.WeTutor.controllers;

import com.example.WeTutor.requests.AuthRequest;
import com.example.WeTutor.requests.SubscriptionRequest;
import com.example.WeTutor.services.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api")
public class StripeController {

    //@Value("${stripe.key}")
    private String stripePublicKey = "sk_test_51J6micBIfwXMPgplD3hYqI20pjOIei4KDTnBGP4pe5oI9eVqTM0oZgaiuBeo3dgfSIJNt2pGStkGhOgtw6sVFCRf00f4bV7I6T";

    @Autowired
    private StripeService stripeService;
    // Pay subscription
    @PostMapping("/checkout")
    public ResponseEntity<Object> subscribe(@RequestBody SubscriptionRequest subscriptionRequest) throws Throwable {
        ResponseEntity<Object> response = stripeService.checkout(subscriptionRequest, stripePublicKey);
        return response;
    }
}
