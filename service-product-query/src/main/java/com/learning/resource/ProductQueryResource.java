package com.learning.resource;

import com.learning.model.entity.Product;
import com.learning.service.ProductQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryResource {

    private final ProductQueryService productQueryService;

    public ProductQueryResource(ProductQueryService productQueryService) {
        this.productQueryService = productQueryService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productQueryService.getAllProducts();
    }
}
