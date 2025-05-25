package com.learning.messaging;

import com.learning.config.TopicConfiguration;
import com.learning.model.EventType;
import com.learning.model.entity.Product;
import com.learning.model.event.ProductEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductEventPublisher {

    private final KafkaTemplate<String, ProductEvent> kafkaTemplate;

    private final TopicConfiguration topicConfiguration;

    public ProductEventPublisher(KafkaTemplate<String, ProductEvent> kafkaTemplate,
                                 TopicConfiguration topicConfiguration) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicConfiguration = topicConfiguration;
    }

    public Product publishProductEvent(Product product, EventType eventType) {
        var productEvent = new ProductEvent(eventType.getEventType(), product);
        kafkaTemplate.send(topicConfiguration.getName(), productEvent);

        return product;
    }
}
