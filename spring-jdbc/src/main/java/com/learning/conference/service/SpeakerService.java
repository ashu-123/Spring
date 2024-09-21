package com.learning.conference.service;

import com.learning.conference.model.Speaker;

import java.util.List;

public interface SpeakerService {
    List<Speaker> findAll();

    Speaker create(Speaker speaker);
}
