package com.example.WeTutor.services;

import com.example.WeTutor.entities.User;
import com.example.WeTutor.repositories.SubscriptionRepository;
import com.example.WeTutor.repositories.UserRepository;
import com.example.WeTutor.requests.SubscriptionRequest;
import com.stripe.model.Customer;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;

public class StipeServiceTest {
    @Mock
    private SubscriptionRepository subscriptionRepository;
    @Mock
    private UserRepository userRepository;
    @Autowired
    private StripeService stripeService;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;
    private SubscriptionRequest request;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        stripeService = new StripeService(subscriptionRepository, userRepository);
        request = new SubscriptionRequest(
            "123456789",
                05,
                2025,
                "123",
                "meyoseph@gmail.com"
        );
    }

    @Test
    void itShouldValidateInputWhenEmailIsNull(){
        request.setUserEmail("");
        JSONObject response = stripeService.validateInputs(request);
        JSONObject errorObject = new JSONObject();
        errorObject.put("email", "Email is required");
        assertThat(response).isEqualTo(errorObject);
    }

    @Test
    void itShouldValidateInputWhenCardNumberIsNull(){
        request.setCardNumber("");
        JSONObject response = stripeService.validateInputs(request);
        JSONObject errorObject = new JSONObject();
        errorObject.put("cardNumber", "Card number is required");
        assertThat(response).isEqualTo(errorObject);
    }

    @Test
    void itShouldValidateInputWhenExpireationMonthIsZero(){
        request.setExpirationMonth(0);
        JSONObject response = stripeService.validateInputs(request);
        JSONObject errorObject = new JSONObject();
        errorObject.put("expirationMonth", "Expiration month is invalid");
        assertThat(response).isEqualTo(errorObject);
    }

    @Test
    void itShouldValidateInputWhenExpireationYearIsZero(){
        request.setExpirationYear(0);
        JSONObject response = stripeService.validateInputs(request);
        JSONObject errorObject = new JSONObject();
        errorObject.put("expirationYear", "Card has already been expired!");
        assertThat(response).isEqualTo(errorObject);
    }

    @Test
    void itShouldValidateInputWhenCVCIsNull(){
        request.setCvc("");
        JSONObject response = stripeService.validateInputs(request);
        JSONObject errorObject = new JSONObject();
        errorObject.put("cvc", "CVC is required");
        assertThat(response).isEqualTo(errorObject);
    }

    @Test
    void itShouldValidateHelperInput(){
        boolean bad = stripeService.validationHelper("");
        assertThat(bad).isEqualTo(false);
        boolean bad2 = stripeService.validationHelper(null);
        assertThat(bad2).isEqualTo(false);
        boolean good = stripeService.validationHelper("test");
        assertThat(good).isEqualTo(true);
    }

    @Test
    void itShouldCallCreateCustomer(){
        StripeService mockStripeService = mock(StripeService.class);
        mockStripeService.createCustomer(request.getUserEmail(), "1234");
        verify(mockStripeService, times(1)).createCustomer(request.getUserEmail(), "1234");
    }

    @Test
    void itShouldCallCheckout(){
        StripeService mockStripeSerivce = mock(StripeService.class);
        mockStripeSerivce.checkout(request, "1234");
        verify(mockStripeSerivce, times(1)).checkout(request, "1234");
    }

    @Test
    void itShouldCallProcessPayment(){
        StripeService mockStripeSerivce = mock(StripeService.class);
        mockStripeSerivce.processPayment(request, new Customer());
        verify(mockStripeSerivce, times(1)).processPayment(request, new Customer());
    }
}
