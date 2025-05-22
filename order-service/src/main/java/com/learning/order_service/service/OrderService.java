package com.learning.order_service.service;

import com.learning.order_service.mapper.OrderMapper;
import com.learning.order_service.mapper.OutboxMapper;
import com.learning.order_service.model.dto.OrderDto;
import com.learning.order_service.model.entity.Order;
import com.learning.order_service.model.entity.Outbox;
import com.learning.order_service.repository.OrderRepository;
import com.learning.order_service.repository.OutboxRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OutboxRepository outboxRepository;

    public OrderService(OrderRepository orderRepository, OutboxRepository outboxRepository) {
        this.orderRepository = orderRepository;
        this.outboxRepository = outboxRepository;
    }

    @Transactional
    public Order createOrder(OrderDto orderDto) {
        Order order = orderRepository.save(OrderMapper.fromDtoToEntity(orderDto));
        Outbox outbox = OutboxMapper.fromOrderToOutboxEntity(order);
        outboxRepository.save(outbox);

        return order;
    }
}
