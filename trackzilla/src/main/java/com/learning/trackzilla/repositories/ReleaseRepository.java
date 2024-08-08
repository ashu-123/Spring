package com.learning.trackzilla.repositories;

import com.learning.trackzilla.model.Release;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseRepository extends MongoRepository<Release, String>{
}
