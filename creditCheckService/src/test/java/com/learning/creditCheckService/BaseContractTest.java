package com.learning.creditCheckService;

import com.learning.creditCheckService.controller.CreditCheckController;
import com.learning.creditCheckService.model.CreditCheckResponse;
import com.learning.creditCheckService.service.CreditCheckService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeAll;

import static com.learning.creditCheckService.model.CreditCheckResponse.Score.HIGH;
import static com.learning.creditCheckService.model.CreditCheckResponse.Score.LOW;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseContractTest {

    @BeforeAll
    public static void setup() {
        final CreditCheckService creditCheckService = mock(CreditCheckService.class);
        when(creditCheckService.doCreditCheck(1234)).thenReturn(new CreditCheckResponse(HIGH, "550e8400-e29b-41d4-a716-446655440000"));
        when(creditCheckService.doCreditCheck(4444)).thenReturn(new CreditCheckResponse(LOW, "550e8400-e29b-41d4-a716-446655440000"));
        RestAssuredMockMvc.standaloneSetup(new CreditCheckController(creditCheckService));
    }
}
