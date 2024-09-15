package com.learning.credit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureStubRunner(ids = "com.learning:creditCheckService:+:stubs:8080", stubsMode = LOCAL)
class CreditApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldGrantApplicationWhenCreditScoreIsHigh() throws Exception{
		mockMvc.perform(
				post("/credit-card-applications")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{" +
								"\"citizenNumber\": 1234" + "," +
								"\"cardType\": \"GOLD\"" +
								"}"
						))
				.andDo(print())
				.andExpect(status().isOk())
//				.andDo(print())
				.andExpect(jsonPath("$.status").value("GRANTED"))
				.andExpect(jsonPath("$.uuid").value(notNullValue()))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void shouldDenyApplicationWhenCreditScoreIsLow() throws Exception{
		mockMvc.perform(
				post("/credit-card-applications")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{" +
								"\"citizenNumber\": 4444" + "," +
								"\"cardType\": \"GOLD\"" +
								"}"
						))
				.andDo(print())
				.andExpect(status().isOk())
//				.andDo(print())
				.andExpect(jsonPath("$.status").value("DENIED"))
				.andExpect(jsonPath("$.uuid").value(notNullValue()))
//				.andExpect(content().json(
//						"{" +
//								"\"status\": \"DENIED\"" + "," +
//								"\"uuid\": \"550e8400-e29b-41d4-a716-446655440000\"" +
//								"}"))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

}
