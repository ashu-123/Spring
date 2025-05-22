package com.learning.order_service.mapper;

import com.learning.order_service.model.dto.OrderDto;
import com.learning.order_service.model.entity.Order;

import java.util.Date;

public class OrderMapper {

    public static Order fromDtoToEntity(OrderDto orderDto) {
        System.out.println(orderDto);
        return Order.builder()
                .name(orderDto.getName())
                .productType(orderDto.getProductType())
                .orderDate(new Date())
                .price(orderDto.getPrice())
                .quantity(orderDto.getQuantity())
                .consumerId(orderDto.getConsumerId())
                .build();
    }
}
