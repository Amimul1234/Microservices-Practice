package com.rahi.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public void registerCustomer( CustomerRegistrationRequest customerRegistrationRequest ) {

        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.getFirstName())
                .lastName(customerRegistrationRequest.getLastName())
                .email(customerRegistrationRequest.getEmail())
                .build();


        //TODO: check if email valid
        //TODO: check if email not taken
        //TODO: store customer in db
    }
}
