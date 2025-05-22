package com.learning.order_poller.messaging;

import com.learning.order_poller.config.TopicConfiguration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class OrderEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final TopicConfiguration topicConfiguration;

    public OrderEventPublisher(KafkaTemplate<String, String> kafkaTemplate,
                               TopicConfiguration topicConfiguration) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicConfiguration = topicConfiguration;
    }

    public void publish(String payload) {
        CompletableFuture<SendResult<String, String>> result = kafkaTemplate.send(topicConfiguration.getName(), payload);
        result.whenComplete((res, ex) -> {
            if (ex == null) System.out.println("Sent message");
            else System.out.println("Sending failed");
        });
    }
}
