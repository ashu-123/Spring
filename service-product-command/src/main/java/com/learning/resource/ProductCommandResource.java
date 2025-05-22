package com.learning.resource;

import com.learning.model.entity.Product;
import com.learning.service.ProductCommandService;
import org.bson.types.ObjectId;
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
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
        return productCommandService.updateProduct(new ObjectId(id), product);
    }
}
