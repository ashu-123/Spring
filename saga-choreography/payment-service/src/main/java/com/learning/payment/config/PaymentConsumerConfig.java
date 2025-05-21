package com.learning.payment.config;

import com.learning.model.event.OrderEvent;
import com.learning.model.event.OrderStatus;
import com.learning.model.event.PaymentEvent;
import com.learning.payment.service.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class PaymentConsumerConfig {

    private final PaymentService paymentService;

    public PaymentConsumerConfig(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Bean
    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor() {
        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
    }

    private Mono<PaymentEvent> processPayment(OrderEvent orderEvent) {
        if(OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())) {
            return Mono.fromSupplier(() -> this.paymentService.newOrderEvent(orderEvent));
        }
        else {
            return Mono.fromRunnable(() -> this.paymentService.cancelOrderEvent(orderEvent));
        }
    }
}
