package com.learning.fundamentals.service;

import com.learning.fundamentals.entity.Application;

import java.util.List;

public interface ApplicationWebService {

    List<Application> listApplications();
    Application findApplication(long id);

}
