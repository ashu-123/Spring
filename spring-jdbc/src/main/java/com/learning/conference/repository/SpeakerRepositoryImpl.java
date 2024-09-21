package com.learning.conference.repository;

import com.learning.conference.model.Speaker;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("speakerRepository")
public class SpeakerRepositoryImpl implements SpeakerRepository {

    private final JdbcTemplate jdbcTemplate;

    public SpeakerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Speaker> findAll() {

        RowMapper<Speaker> rowMapper = (rs, rowNum) -> {
            Speaker speaker = new Speaker();
            speaker.setId(rs.getInt("id"));
            speaker.setName(rs.getString("name"));
            return speaker;
        };

        List<Speaker> speakers = jdbcTemplate.query("select * from speaker", rowMapper);
        return speakers;
//        Speaker speaker = new Speaker();
//        speaker.setName("Bryan Hansen");
//        speaker.setSkill("Java");
//        List<Speaker> speakers = new ArrayList<>();
//        speakers.add(speaker);
//        return speakers;
    }

    @Override
    public Speaker create(Speaker speaker) {
        jdbcTemplate.update("INSERT INTO speaker (name) values (?)", speaker.getName());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.setTableName("speaker");

        List<String> columnNames = new ArrayList<>();
        columnNames.add("name");

        Map<String, Object> data = new HashMap<>();
        data.put("name", speaker.getName());

        insert.setGeneratedKeyName("id");
        Number key = insert.executeAndReturnKey(data);

        System.out.println(key);
        return null;
    }
}
