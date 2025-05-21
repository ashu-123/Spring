package com.learning.order.resource;

import com.learning.model.dto.OrderRequestDto;
import com.learning.order.entiry.PurchaseOrder;
import com.learning.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderResource {

    private final OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDto order) {
        return orderService.createOrder(order);
    }

    @GetMapping
    public List<PurchaseOrder> getAllOrders() {
        return orderService.getAllOrders();
    }
}
