package com.learning.payment.service;

import com.learning.model.dto.OrderRequestDto;
import com.learning.model.dto.PaymentRequestDto;
import com.learning.model.event.OrderEvent;
import com.learning.model.event.PaymentEvent;
import com.learning.model.event.PaymentStatus;
import com.learning.payment.entity.UserBalance;
import com.learning.payment.entity.UserTransaction;
import com.learning.payment.repository.UserBalanceRepository;
import com.learning.payment.repository.UserTransactionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Service
public class PaymentService {

    private final UserBalanceRepository userBalanceRepository;

    private final UserTransactionRepository userTransactionRepository;

    public PaymentService(UserBalanceRepository userBalanceRepository, UserTransactionRepository userTransactionRepository) {
        this.userBalanceRepository = userBalanceRepository;
        this.userTransactionRepository = userTransactionRepository;
    }

    @PostConstruct
    public void initializeUserBalance() {
        userBalanceRepository.saveAll(Stream.of(new UserBalance(101, 600),
                        new UserBalance(102, 345),
                        new UserBalance(103, 546),
                        new UserBalance(104, 890))
                .toList());
    }

    @Transactional
    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
        OrderRequestDto orderRequestDto = orderEvent.getOrderRequestDto();
        var paymentRequestDto = new PaymentRequestDto(orderRequestDto.getUserId(),
                orderRequestDto.getProductId(),
                orderRequestDto.getOrderId(),
                orderRequestDto.getAmount());

        return userBalanceRepository.findById(orderRequestDto.getUserId())
                .filter(ub -> ub.getPrice() > orderRequestDto.getAmount())
                .map(ub -> {
                    ub.setPrice(ub.getPrice() - orderRequestDto.getAmount());
                    userTransactionRepository.save(new UserTransaction(orderRequestDto.getUserId(), orderRequestDto.getOrderId(), orderRequestDto.getAmount()));
                    return new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_COMPLETED);
                })
                .orElse(new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_FAILED));
    }

    @Transactional
    public void cancelOrderEvent(OrderEvent orderEvent) {
        userTransactionRepository.findById(orderEvent.getOrderRequestDto().getOrderId())
                .ifPresent(ut -> {
                    userTransactionRepository.delete(ut);
                    userBalanceRepository.findById(ut.getUserId()).ifPresent(ub -> ub.setPrice(ub.getPrice() + ut.getPrice()));
                });
    }
}
