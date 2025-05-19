package com.learning.model.event;

import com.learning.model.dto.PaymentRequestDto;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
public class PaymentEvent implements Event{

    private UUID eventId = UUID.randomUUID();
    private Date date = new Date();
    private PaymentRequestDto paymentRequestDto;
    private PaymentStatus paymentStatus;

    public PaymentEvent(PaymentRequestDto paymentRequestDto, PaymentStatus paymentStatus) {
        this.paymentRequestDto = paymentRequestDto;
        this.paymentStatus = paymentStatus;
    }

    @Override
    public UUID getUUID() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return date;
    }
}
