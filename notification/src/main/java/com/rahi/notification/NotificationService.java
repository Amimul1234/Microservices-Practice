package com.rahi.notification;

import com.rahi.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send( NotificationRequest notificationRequest ) {
        notificationRepository.save(
                Notification.builder()
                        .customerId(notificationRequest.getCustomerId())
                        .toCustomerEmail(notificationRequest.getToCustomerName())
                        .sender("Rahi")
                        .message(notificationRequest.getMessage())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }

}
