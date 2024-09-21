package com.learning.conference.service;

import com.learning.conference.model.Speaker;
import com.learning.conference.repository.SpeakerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("speakerService")
public class SpeakerServiceImpl implements SpeakerService {

    private SpeakerRepository speakerRepository;

    public SpeakerServiceImpl(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    @Override
    public List<Speaker> findAll() {
        return speakerRepository.findAll();
    }

    @Override
    public Speaker create(Speaker speaker) {
        return speakerRepository.create(speaker);
    }
}
