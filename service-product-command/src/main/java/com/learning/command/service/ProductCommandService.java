package com.learning.command.service;

import com.learning.command.model.entity.Product;
import com.learning.command.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCommandService {

    private final ProductRepository repository;

    public ProductCommandService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(ObjectId id, Product product) {
        return repository.findById(id)
                .map(p -> {
                    p.setName(product.getName());
                    p.setPrice(product.getPrice());
                    p.setDescription(product.getDescription());
                    return p;
                })
                .map(repository::save)
                .orElseThrow(RuntimeException::new);
    }
}
