package com.learning.conference.repository;

import com.learning.conference.model.Speaker;

import java.util.List;

public interface SpeakerRepository {
    List<Speaker> findAll();

    Speaker create(Speaker speaker);

    Speaker getSpeaker(int id);

    Speaker updateSpeaker(Speaker speaker);

    void deleteSpeaker(int id);
}
