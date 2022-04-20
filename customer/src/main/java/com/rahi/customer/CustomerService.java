package com.rahi.customer;

import com.rahi.clients.fraud.FraudCheckResponse;
import com.rahi.clients.fraud.FraudClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;

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

    }
}
