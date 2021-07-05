package com.example.WeTutor.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PAYMENT_TBL")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", unique = true, nullable = false)
    private String id;

    private String parentEmail;
    private String customerId;

    private LocalDateTime subscriptionDate;

    public Payment(String parentEmail, String customerId) {
        this.parentEmail = parentEmail;
        this.customerId = customerId;
        this.subscriptionDate = LocalDateTime.now();
    }
}
