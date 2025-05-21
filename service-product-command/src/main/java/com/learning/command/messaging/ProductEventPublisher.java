package com.learning.command.messaging;

import com.learning.command.model.entity.Product;
import com.learning.command.model.event.ProductEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ProductEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Product publishProductEvent(Product product, String eventType) {
        var productEvent = new ProductEvent(eventType, product);
        kafkaTemplate.send("product-event-topic", productEvent);

        return product;
    }
}
