package com.learning.logging_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

@Service
@Slf4j
public class LoggingService {

    public static final String LOG_PATH = "/logs/book-service.log";

    public LoggingService() {
        log.info("Logging service booted up...");
    }

    @Scheduled(fixedRate = 60000)
    public void processLogs() {
        log.info("Logging service scheduler triggered at {} ", new Date());
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(LOG_PATH))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                //simulate pushing logs to a centralized service e.g, splunk/ELK
                log.info("Processing log : " + line);
            }
        } catch (IOException ioException) {
            log.error(ioException.getMessage());
        }
    }
}
