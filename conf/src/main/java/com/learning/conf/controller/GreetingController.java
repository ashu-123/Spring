package com.learning.conf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class GreetingController {

    @GetMapping("greeting")
    public String greeting(Map<String, Object> model) {
        System.out.println("hi");
        model.put("message", "Hello Ashutosh");
        return "greeting";
    }
}
