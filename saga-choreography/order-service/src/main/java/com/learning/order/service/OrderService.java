package com.learning.order.service;

import com.learning.model.dto.OrderRequestDto;
import com.learning.model.event.OrderStatus;
import com.learning.order.entiry.PurchaseOrder;
import com.learning.order.mapper.PurchaseOrderMapper;
import com.learning.order.messaging.OrderEventPublisher;
import com.learning.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderEventPublisher orderEventPublisher;

    public OrderService(OrderRepository orderRepository, OrderEventPublisher orderEventPublisher) {
        this.orderRepository = orderRepository;
        this.orderEventPublisher = orderEventPublisher;
    }

    @Transactional
    public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {
        var purchaseOrder = orderRepository.save(PurchaseOrderMapper.orderDtoToEntity(orderRequestDto));
        orderRequestDto.setOrderId(purchaseOrder.getId());
        orderEventPublisher.publishOrderEvent(orderRequestDto, OrderStatus.ORDER_CREATED);
        return purchaseOrder;
    }

    public List<PurchaseOrder> getAllOrders() {
        return orderRepository.findAll();
    }
}
