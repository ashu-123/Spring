package com.learning.messaging;

import com.learning.model.event.ProductEvent;
import com.learning.service.ProductQueryService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventConsumer {

    private final ProductQueryService productQueryService;

    public ProductEventConsumer(ProductQueryService productQueryService) {
        this.productQueryService = productQueryService;
    }

    @KafkaListener(topics = "product-event-topic", groupId = "product-event-group")
    public void processProductEvent(ProductEvent productEvent) {
        if(productEvent.getEventType().equals("PRODUCT-CREATED")) {
            var productCreated = productQueryService.createProduct(productEvent.getProduct());
        }
        else {
            var updatedProduct = productQueryService.updateProduct(productEvent.getProduct());
        }
    }
}
