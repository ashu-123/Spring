package com.learning.order.entiry;

import com.learning.model.event.OrderStatus;
import com.learning.model.event.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "PURCHASE_ORDER")
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

}
