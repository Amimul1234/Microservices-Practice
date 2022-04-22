package com.rahi.notification;

import com.rahi.amqp.RabbitMqMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {"com.rahi.amqp", "com.rahi.notification"}
)
@EnableEurekaClient
@EnableFeignClients
public class NotificationApplication {

    public static void main( String[] args ) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner( RabbitMqMessageProducer rabbitMqMessageProducer,
                                         NotificationConfig notificationConfig ) {
        return args -> rabbitMqMessageProducer.publish(
                "foo", notificationConfig.getInternalExchange(),
                notificationConfig.getInternalNotificationRoutingKey());
    }
}
