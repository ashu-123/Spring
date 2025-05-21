package com.learning.command.model.event;

import com.learning.command.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEvent {

    private String eventType;

    private Product product;
}
