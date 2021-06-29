package com.example.WeTutor.repositories;

import com.example.WeTutor.entities.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriptionRepository extends MongoRepository<Payment,Integer> {
    Payment findPaymentByParentEmail(String parentEmail);
}
