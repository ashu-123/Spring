package com.learning.conf.controller;

import com.learning.conf.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @GetMapping("hello")
    public User getUser() {
        User user = new User();
        user.setId(1);;
        user.setName("Ashutosh");;
        return user;
    }
}
