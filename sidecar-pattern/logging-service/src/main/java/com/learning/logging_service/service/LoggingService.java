package com.learning.logging_service.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Service
public class LoggingService {

    public static final String LOG_PATH = "/logs/book-service.log";

    @Scheduled(fixedRate = 60000)
    public void processLogs() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(LOG_PATH))) {
            String line;

            while((line = bufferedReader.readLine())!=null) {
                //simulate pushing logs to a centralized service e.g, splunk/ELK
                System.out.println("Processing log : " + line);
            }
        }
        catch (IOException ioException) {

        }
    }
}
