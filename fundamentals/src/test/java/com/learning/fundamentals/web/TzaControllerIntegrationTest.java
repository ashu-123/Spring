package com.learning.fundamentals.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit5.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TzaWebController.class)
@AutoConfigureMockMvc
public class TzaControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllApplications() throws Exception {
        ResponseEntity response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/tza/applications/", Object.class);
        System.out.println(response.toString());

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void getAllTickets() throws Exception {
        ResponseEntity<List> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/tza/tickets/", List.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
