package com.learning.order.mapper;

import com.learning.model.dto.OrderRequestDto;
import com.learning.model.event.OrderStatus;
import com.learning.order.entiry.PurchaseOrder;

public class PurchaseOrderMapper {

    public static PurchaseOrder orderDtoToEntity(OrderRequestDto orderRequestDto) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setPrice(orderRequestDto.getAmount());
        purchaseOrder.setUserId(orderRequestDto.getUserId());
        purchaseOrder.setProductId(orderRequestDto.getProductId());
        purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);
        return purchaseOrder;
    }

    public static OrderRequestDto orderEntityToDto(PurchaseOrder purchaseOrder) {
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setUserId(purchaseOrder.getUserId());
        orderRequestDto.setOrderId(purchaseOrder.getId());
        orderRequestDto.setAmount(purchaseOrder.getPrice());
        orderRequestDto.setProductId(purchaseOrder.getProductId());
        return orderRequestDto;
    }
}
