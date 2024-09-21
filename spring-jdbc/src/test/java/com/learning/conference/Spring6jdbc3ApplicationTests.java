package com.learning.conference;

import com.learning.conference.model.Speaker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class Spring6jdbc3ApplicationTests {

    @Test
    void testCreateSpeaker() {
        RestTemplate restTemplate = new RestTemplate();

        Speaker speaker = new Speaker();
        speaker.setName("Ashufgf");
        speaker = restTemplate.postForObject(
                "http://localhost:8080/speaker", speaker, Speaker.class);

        System.out.println(speaker);
    }

    @Test
    void testUpdateSpeaker() {
        RestTemplate restTemplate = new RestTemplate();

        Speaker speaker = new Speaker();
        speaker.setId(1);
        speaker.setName("Ashufgf");
        restTemplate.put("http://localhost:8080/speaker", speaker);

        System.out.println(speaker);
    }

    @Test
    void testGetSpeakers() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Speaker>> speakersResponse = restTemplate.exchange(
                "http://localhost:8080/speaker", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Speaker>>() {
                });

        assertTrue(speakersResponse.getBody() != null, "Body is null");

        List<Speaker> speakers = speakersResponse.getBody();

        for (Speaker speaker : speakers) {

            System.out.println("Speaker id: " + speaker.getId() + " Speaker name: " + speaker.getName());
        }
    }

    @Test
    void testGetSpeaker() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Speaker> speakersResponse = restTemplate.exchange(
                "http://localhost:8080/speaker/1", HttpMethod.GET,
                null, new ParameterizedTypeReference<Speaker>() {
                });

        assertTrue(speakersResponse.getBody() != null, "Body is null");

        Speaker speaker = speakersResponse.getBody();


            System.out.println("Speaker id: " + speaker.getId() + " Speaker name: " + speaker.getName());

    }
}
