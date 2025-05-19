package com.learning.order.config;

import com.learning.model.event.OrderStatus;
import com.learning.model.event.PaymentStatus;
import com.learning.order.entiry.PurchaseOrder;
import com.learning.order.mapper.PurchaseOrderMapper;
import com.learning.order.messaging.OrderEventPublisher;
import com.learning.order.repository.OrderRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Configuration
public class OrderStatusUpdateHandler {

    private final OrderEventPublisher orderEventPublisher;

    private final OrderRepository orderRepository;

    public OrderStatusUpdateHandler(OrderEventPublisher orderEventPublisher,
                                    OrderRepository orderRepository) {
        this.orderEventPublisher = orderEventPublisher;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void updateOrder(int id, Consumer<PurchaseOrder> consumer) {
        orderRepository.findById(id).ifPresent(consumer.andThen(this::updateOrder));
    }

    private void updateOrder(PurchaseOrder purchaseOrder) {
        boolean isPaymentComplete = PaymentStatus.PAYMENT_COMPLETED.equals(purchaseOrder.getPaymentStatus());
        OrderStatus orderStatus = isPaymentComplete ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED;
        purchaseOrder.setOrderStatus(orderStatus);

        if (!isPaymentComplete) {
            orderEventPublisher.publishOrderEvent(PurchaseOrderMapper.orderEntityToDto(purchaseOrder), orderStatus);
        }
    }


}