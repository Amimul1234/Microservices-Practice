package com.rahi.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {"com.rahi.amqp", "com.rahi.customer"}
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = {"com.rahi.clients.fraud", "com.rahi.clients.notification"}
)
public class CustomerApplication {
    public static void main( String[] args ) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
