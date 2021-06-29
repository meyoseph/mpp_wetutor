package com.example.WeTutor.services;

import com.example.WeTutor.entities.Payment;
import com.example.WeTutor.repositories.SubscriptionRepository;
import com.example.WeTutor.repositories.UserRepository;
import com.example.WeTutor.requests.SubscriptionRequest;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Customer;
import com.stripe.model.Subscription;
import com.stripe.model.Token;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class StripeService {

    private SubscriptionRepository subscriptionRepository;
    private UserRepository userRepository;

    public ResponseEntity<Object> checkout(SubscriptionRequest subscriptionRequest, String stripePublicKey) {
        Customer customer = null;
        Payment payment = subscriptionRepository.findPaymentByParentEmail(subscriptionRequest.getUserEmail());

        // Get user email for creating a customer in stripe
        String email = userRepository.findByEmail(subscriptionRequest.getUserEmail()).getEmail();
        JSONObject response = new JSONObject();
        // Check if the parent has already subscribed
        if(payment != null){
            response.put("payment","You have already subscribed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }else{
            ResponseEntity<Object> r = null;
            // Create customer in stripe account
            customer = createCustomer(email, stripePublicKey);
            if(customer != null){
                // Process payment
                r = processPayment(subscriptionRequest, customer);
            }
            return r;
        }
    }

    public Customer createCustomer(String email, String stripePublicKey){
        Stripe.apiKey = stripePublicKey;
        Customer customer = null;
        Map<String, Object> customerMap = new HashMap<String, Object>();
        customerMap.put("email", email);
        customerMap.put("description", "Subscription payment for WeTutor");
        customerMap.put("payment_method", "pm_card_visa");

        try {
            customer = Customer.create(customerMap);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public ResponseEntity<Object> processPayment (SubscriptionRequest subscriptionRequest, Customer customer){
        JSONObject response = new JSONObject();
        // get request data
        String email = subscriptionRequest.getUserEmail();
        String creditCardNumber = subscriptionRequest.getCardNumber();
        int month = subscriptionRequest.getExpirationMonth();
        int year = subscriptionRequest.getExpirationYear();
        String cvc = subscriptionRequest.getCvc();

        try{

            Map<String, Object> customerData = new HashMap<String, Object>();
            customerData.put("number",creditCardNumber);
            customerData.put("exp_month", month);
            customerData.put("exp_year", year);
            customerData.put("cvc",cvc);

            Map<String, Object> params = new HashMap<>();
            params.put("card", customerData);
            Token token = Token.create(params);

            List<String> expandList = new ArrayList<>();
            expandList.add("sources");

            Map<String, Object> retrieveParams = new HashMap<>();
            retrieveParams.put("expand", expandList);

            Customer newCustomer =
                    Customer.retrieve(
                            customer.getId(),
                            retrieveParams,
                            null
                    );
            Map<String, Object> sourceParams = new HashMap<>();

            sourceParams.put("source", token.getId());
            newCustomer.getSources().create(sourceParams);

            // Create Subscription
            List<Object> items = new ArrayList<>();
            Map<String, Object> item1 = new HashMap<>();
            item1.put(
                    "price",
                    "price_1J6oIsBIfwXMPgplHZsAS1mO"
            );
            items.add(item1);
            Map<String, Object> subscriptionParams = new HashMap<>();
            subscriptionParams.put("customer", customer.getId());
            subscriptionParams.put("items", items);

            Subscription.create(subscriptionParams);
            Payment payment = new Payment(email,customer.getId());
            subscriptionRepository.save(payment);
            response.put("success",true);
            response.put("message","Subscription made successfully!");
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }catch (StripeException e) {
            e.printStackTrace();
            response.put("success",false);
            response.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
