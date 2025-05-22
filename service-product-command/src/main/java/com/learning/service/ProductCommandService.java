package com.learning.service;

import com.learning.messaging.ProductEventPublisher;
import com.learning.model.entity.Product;
import com.learning.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {

    private final ProductRepository repository;

    private final ProductEventPublisher productEventPublisher;

    public ProductCommandService(ProductRepository repository,
                                 ProductEventPublisher productEventPublisher) {
        this.repository = repository;
        this.productEventPublisher = productEventPublisher;
    }

    public Product createProduct(Product product) {
        var productCreated = repository.save(product);
        productEventPublisher.publishProductEvent(productCreated, "PRODUCT-CREATED");
        return productCreated;
    }

    public Product updateProduct(ObjectId id, Product product) {
        var productUpdated = repository.findById(id)
                .map(p -> {
                    p.setName(product.getName());
                    p.setPrice(product.getPrice());
                    p.setDescription(product.getDescription());
                    return p;
                })
                .map(repository::save)
                .orElseThrow(RuntimeException::new);

        productEventPublisher.publishProductEvent(productUpdated, "PRODUCT-UPDATED");
        return productUpdated;
    }
}
