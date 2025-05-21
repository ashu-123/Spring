package com.learning.command.resource;

import com.learning.command.model.entity.Product;
import com.learning.command.service.ProductCommandService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductCommandResource {

    private final ProductCommandService productCommandService;

    public ProductCommandResource(ProductCommandService productCommandService) {
        this.productCommandService = productCommandService;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productCommandService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(long id, @RequestBody Product product) {
        return productCommandService.updateProduct(id, product);
    }
}
