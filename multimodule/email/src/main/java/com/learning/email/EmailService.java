package com.learning.email;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail() {
        System.out.println("Sending");
    }
}
