package com.learning.service;

import com.learning.dto.enums.OrderStatus;
import com.learning.dto.request.OrderRequest;
import com.learning.dto.response.OrderResponse;
import com.learning.entity.OrderEvent;
import com.learning.publisher.OrderEventKafkaPublisher;
import com.learning.repository.OrderEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderEventRepository repository;

    @Autowired
    private OrderEventKafkaPublisher publisher;


    // Handle order creation
    public OrderResponse placeAnOrder(OrderRequest orderRequest) {
        String orderId = UUID.randomUUID().toString().split("-")[0];
        orderRequest.setOrderId(orderId);
        //do request validation and real business logic
        //save that event and publish kafka messages
        OrderEvent orderEvent=new OrderEvent(orderId,OrderStatus.CREATED,"Order created successfully", LocalDateTime.now());
        saveAndPublishEvents(orderEvent);
        return new OrderResponse(orderId, OrderStatus.CREATED);
    }

    // Handle order confirmation
    public OrderResponse confirmOrder(String orderId) {
        OrderEvent orderEvent=new OrderEvent(orderId,OrderStatus.CONFIRMED,"Order confirmed successfully", LocalDateTime.now());
        saveAndPublishEvents(orderEvent);
        return new OrderResponse(orderId, OrderStatus.CONFIRMED);
    }

    private void saveAndPublishEvents(OrderEvent orderEvent){
        repository.save(orderEvent);
        publisher.sendOrderEvent(orderEvent);
    }


}
