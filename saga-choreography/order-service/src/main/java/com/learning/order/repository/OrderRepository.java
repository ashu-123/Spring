package com.learning.order.repository;

import com.learning.order.entiry.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer> {
}
