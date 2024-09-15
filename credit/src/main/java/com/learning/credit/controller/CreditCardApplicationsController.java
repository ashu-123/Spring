package com.learning.credit.controller;

import com.learning.credit.model.ApplyForCreditCardRequest;
import com.learning.credit.model.ApplyForCreditCardResponse;
import com.learning.credit.model.CreditCheckRequest;
import com.learning.credit.model.CreditCheckResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.learning.credit.model.ApplyForCreditCardRequest.CardType.GOLD;
import static com.learning.credit.model.ApplyForCreditCardResponse.Status.DENIED;
import static com.learning.credit.model.ApplyForCreditCardResponse.Status.GRANTED;
import static com.learning.credit.model.CreditCheckResponse.Score.HIGH;
import static com.learning.credit.model.CreditCheckResponse.Score.LOW;

@RestController
public class CreditCardApplicationsController {

    private final RestTemplate restTemplate;

    public CreditCardApplicationsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/credit-card-applications")
    public ApplyForCreditCardResponse applyForCreditCard(@RequestBody final ApplyForCreditCardRequest applyForCreditCardRequest,
                                                         @Value("${creditcheckservice.baseurl}") String baseUrl) {
        int citizenNumber = applyForCreditCardRequest.getCitizenNumber();
        String uri = UriComponentsBuilder.fromHttpUrl(baseUrl).path("credit-scores").toUriString();
        var creditCheckRequest = new CreditCheckRequest(citizenNumber);
        var creditCheckResponse = restTemplate.postForObject(uri,
                creditCheckRequest,
                CreditCheckResponse.class);
        if(!creditCheckRequest.getUuid().equals(creditCheckResponse.getUuid())) throw new RuntimeException("Error");
        if (creditCheckResponse.getScore() == HIGH && applyForCreditCardRequest.getCardType() == GOLD) {
            var response = new ApplyForCreditCardResponse(GRANTED);
            response.setUuid(creditCheckResponse.getUuid());
            return response;
        }
        else if(creditCheckResponse.getScore() == LOW && applyForCreditCardRequest.getCardType() == GOLD) {
            var response =  new ApplyForCreditCardResponse(DENIED);
            response.setUuid(creditCheckResponse.getUuid());
            return response;
        }
        throw new RuntimeException("Error occurred");
    }
}
