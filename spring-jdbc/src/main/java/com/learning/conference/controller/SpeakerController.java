package com.learning.conference.controller;

import com.learning.conference.model.Speaker;
import com.learning.conference.service.SpeakerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpeakerController {

    private SpeakerService speakerService;

    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @GetMapping
    public List<Speaker> getSpeakers() {
        return speakerService.findAll();
    }
}
