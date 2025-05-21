package com.learning.order.config;

import com.learning.model.event.PaymentEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class PaymentEventConsumerConfig {


    private final OrderStatusUpdateHandler orderStatusUpdateHandler;

    public PaymentEventConsumerConfig(OrderStatusUpdateHandler orderStatusUpdateHandler) {
        this.orderStatusUpdateHandler = orderStatusUpdateHandler;
    }

    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer() {
        return paymentEvent -> {
          orderStatusUpdateHandler.updateOrder(paymentEvent.getPaymentRequestDto().getOrderId(),
                  purchaseOrder -> purchaseOrder.setPaymentStatus(paymentEvent.getPaymentStatus()));
        };
    }
}
