package com.learning.fundamentals.web;

import com.learning.fundamentals.repository.ApplicationRepository;
import com.learning.fundamentals.service.ApplicationService;
import com.learning.fundamentals.service.ApplicationWebService;
import com.learning.fundamentals.service.TicketService;
import com.learning.fundamentals.service.TicketWebService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@AutoConfigureWebTestClient
//@AutoConfigureMockMvc
@WebMvcTest(TzaWebController.class)
@Import(TzaWebController.class)
public class TzaControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ApplicationWebService applicationService;

    @MockBean
    ApplicationRepository applicationRepository;

    @MockBean
    TicketWebService ticketService;

    @Test
    public void getAllApplications() throws Exception {

//        webTestClient.get()
//                        .uri("/tza/applications")
//                                .exchange()
//                                        .expectStatus().isOk();
        mockMvc.perform(get("/tza/applications/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[]"));

        verify(applicationService, times(1)).listApplications();
    }

//    @Test
//    public void getAllTickets() throws Exception {
//        mockMvc.perform(get("/tza/tickets/"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(content().json("[]"));
//
//        verify(ticketService, times(1)).listTickets();
//    }
}
