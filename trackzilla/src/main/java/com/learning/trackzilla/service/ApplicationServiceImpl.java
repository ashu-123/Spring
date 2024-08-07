package com.learning.trackzilla.service;


import com.learning.trackzilla.model.Application;
import com.learning.trackzilla.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void addNewApplicationWTemplate(Application application) {
        mongoTemplate.insert(application);
    }

    @Override
    public Application findByIdTemplate(String id){
        return mongoTemplate.findById(id, Application.class);
    }

    @Override
    public void deleteWTemplate(Application application){
        mongoTemplate.remove(application);
    }

    @Override
    public void updateApplicationWTemplate(Application application){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(application.getName()));
        Update update = new Update();
        update.set("name", "Trainer");
        mongoTemplate.updateFirst(query, update, Application.class);
    }

    @Override
    @Transactional
    public void retireApplication(Application application) {
        mongoTemplate.remove(application);

        Query query = new Query();
        query.addCriteria(Criteria.where("appId").is(application.getId()));
        Update update = new Update();
        update.set("status", "Cancel");
        mongoTemplate.updateMulti(query, update, Ticket.class);
    }
}
