package com.learning.credit.controller;

import com.learning.credit.model.ApplyForCreditCardRequest;
import com.learning.credit.model.ApplyForCreditCardResponse;
import com.learning.credit.model.CreditCheckRequest;
import com.learning.credit.model.CreditCheckResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.learning.credit.model.ApplyForCreditCardRequest.CardType.GOLD;
import static com.learning.credit.model.ApplyForCreditCardResponse.Status.GRANTED;
import static com.learning.credit.model.CreditCheckResponse.Score.HIGH;

@RestController
public class CreditCardApplicationsController {

    private final RestTemplate restTemplate;

    public CreditCardApplicationsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/credit-card-applications")
    public ApplyForCreditCardResponse applyForCreditCard(@RequestBody final ApplyForCreditCardRequest applyForCreditCardRequest) {
        int citizenNumber = applyForCreditCardRequest.getCitizenNumber();
        var creditCheckResponse = restTemplate.postForObject("http://localhost:8080/credit-scores",
                new CreditCheckRequest(citizenNumber),
                CreditCheckResponse.class);
        if (creditCheckResponse.getScore() == HIGH && applyForCreditCardRequest.getCardType() == GOLD) {
            return new ApplyForCreditCardResponse(GRANTED);
        }
        throw new RuntimeException("Error occurred");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleMethodArgumentNotValidException(
            Exception error ) {
        System.out.println(error.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }
}
