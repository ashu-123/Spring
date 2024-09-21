package com.learning.conference.controller;

import com.learning.conference.model.Speaker;
import com.learning.conference.service.SpeakerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpeakerController {

    private SpeakerService speakerService;

    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @GetMapping("/speaker")
    public List<Speaker> getSpeakers() {
        return speakerService.findAll();
    }

    @PostMapping("/speaker")
    public Speaker createSpeaker(@RequestBody Speaker speaker) {
        System.out.println(speaker.getName());
        return speakerService.create(speaker);
    }

    @GetMapping("/speaker/{id}")
    public Speaker getSpeaker(@PathVariable("id") int id) {
        return speakerService.getSpeaker(id);
    }

    @PutMapping("/speaker")
    public Speaker updateSpeaker(@RequestBody Speaker speaker) {
        System.out.println(speaker.getName());
        return speakerService.updateSpeaker(speaker);
    }

    @DeleteMapping("/speaker/{id}")
    public Speaker deleteSpeaker(@PathVariable("id") int id) {
        speakerService.deleteSpeaker(id);
        return null;
    }
}
