package com.learning.order_poller.service;

import com.learning.order_poller.messaging.OrderEventPublisher;
import com.learning.order_poller.model.entity.Outbox;
import com.learning.order_poller.repository.OutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
@Slf4j
public class OrderPollerService {

    private final OutboxRepository outboxRepository;

    private final OrderEventPublisher orderEventPublisher;

    public OrderPollerService(OutboxRepository outboxRepository,
                              OrderEventPublisher orderEventPublisher) {
        this.outboxRepository = outboxRepository;
        this.orderEventPublisher = orderEventPublisher;
    }

    @Scheduled(fixedRate = 60000)
    public void pollOutboxAndPublish() {
        var unprocessedOrders = outboxRepository.findByIsProcessedFalse();
        log.info("Number of unprocessed records {}", unprocessedOrders.size());
        for(Outbox unprocessedOrder : unprocessedOrders) {
            try {
                orderEventPublisher.publish(unprocessedOrder.getPayload());
                unprocessedOrder.setIsProcessed(true);
                outboxRepository.save(unprocessedOrder);
            }
            catch (Exception e) {
                log.error("Exception occurred : {}", e.getMessage());
            }
        }
    }
}
