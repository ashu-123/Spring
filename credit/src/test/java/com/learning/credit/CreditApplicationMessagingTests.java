package com.learning.credit;

import com.learning.credit.model.CreditCheckResponse;
import com.learning.credit.repository.CreditRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;

import java.util.UUID;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

@SpringBootTest(webEnvironment = NONE)
@AutoConfigureStubRunner(ids = "com.learning:creditCheckService:+:stubs:8080", stubsMode = LOCAL)
class CreditApplicationMessagingTests {

    @Autowired
    private StubTrigger stubTrigger;

    @Autowired
    private CreditRepository creditRepository;

    @Test
    public void shouldStoreResultsOfACreditCheck() {
        final UUID uuid = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        stubTrigger.trigger("score_of_high");
        assert(creditRepository.getScore(uuid)).equals(CreditCheckResponse.Score.HIGH);
    }

}

