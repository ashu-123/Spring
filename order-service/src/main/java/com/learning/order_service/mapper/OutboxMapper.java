package com.learning.order_service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.order_service.model.entity.Order;
import com.learning.order_service.model.entity.Outbox;
import lombok.SneakyThrows;

public class OutboxMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static Outbox fromOrderToOutboxEntity(Order order) {
        return Outbox.builder()
                .aggregateId(order.getId().toString())
                .createdDate(order.getOrderDate())
                .isProcessed(false)
                .payload(objectMapper.writeValueAsString(order))
                .build();
    }
}
