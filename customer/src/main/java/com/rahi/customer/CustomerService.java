package com.rahi.customer;

import com.rahi.clients.fraud.FraudCheckResponse;
import com.rahi.clients.fraud.FraudClient;
import com.rahi.clients.notification.NotificationClient;
import com.rahi.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer( CustomerRegistrationRequest customerRegistrationRequest ) {

        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.getFirstName())
                .lastName(customerRegistrationRequest.getLastName())
                .email(customerRegistrationRequest.getEmail())
                .build();

        Customer customer1 = customerRepository.save(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer1.getId());

        if (fraudCheckResponse.getIsFraudster()) {
            throw new IllegalStateException("Fraudster");
        }

        //TODO: send notification

        notificationClient.sendNotification(
                new NotificationRequest(
                        customer1.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, welcome to rahi's world", customer.getFirstName())
                ));
    }
}
