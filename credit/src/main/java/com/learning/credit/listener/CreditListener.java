package com.learning.credit.listener;

import com.learning.credit.model.CreditCheckResponse;
import com.learning.credit.repository.CreditRepository;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
public class CreditListener {

    private final CreditRepository creditRepository;

    public CreditListener(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @StreamListener(Sink.INPUT)
    public void receiveScore(CreditCheckResponse creditCheckResponse) {
        creditRepository.save(creditCheckResponse.getUuid() ,creditCheckResponse.getScore());
    }
}
