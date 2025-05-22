package com.learning.order_poller.service;

import com.learning.order_poller.repository.OutboxRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderPollerService {

    private final OutboxRepository outboxRepository;

    public OrderPollerService(OutboxRepository outboxRepository) {
        this.outboxRepository = outboxRepository;
    }

    public void pollOutboxAndPublish() {
        var unprocessedOrders = outboxRepository.findByProcessedFalse();
    }
}
