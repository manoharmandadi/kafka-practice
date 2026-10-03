package com.pm.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    @KafkaListener(topics = "test-topic", groupId = "kafka-client-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
        // Add your message processing logic here
    }
}
