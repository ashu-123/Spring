package com.demo.tickets.repository.jpa;

import com.demo.tickets.model.jpa.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByEventId(int eventId);
}
