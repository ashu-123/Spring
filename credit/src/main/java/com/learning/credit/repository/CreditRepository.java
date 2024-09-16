package com.learning.credit.repository;

import com.learning.credit.model.CreditCheckResponse;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class CreditRepository {

    private final Map<String, CreditCheckResponse.Score> db = new HashMap<>();
    public CreditCheckResponse.Score getScore(UUID uuid) {
        return db.get(uuid.toString());
    }

    public void save(String uuid, CreditCheckResponse.Score score) {
        db.put(uuid, score);
    }
}
