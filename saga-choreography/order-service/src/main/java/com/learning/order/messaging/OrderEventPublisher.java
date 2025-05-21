package com.learning.order.messaging;

import com.learning.model.dto.OrderRequestDto;
import com.learning.model.event.OrderEvent;
import com.learning.model.event.OrderStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
public class OrderEventPublisher {

    private final Sinks.Many<OrderEvent> orderSinks;

    public OrderEventPublisher(Sinks.Many<OrderEvent> orderSinks) {
        this.orderSinks = orderSinks;
    }

    public void publishOrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
        OrderEvent orderEvent = new OrderEvent(orderRequestDto, orderStatus);
        orderSinks.tryEmitNext(orderEvent);
    }
}
