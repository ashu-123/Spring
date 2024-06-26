package com.learning.fundamentals;

import com.learning.fundamentals.entity.Application;
import com.learning.fundamentals.repository.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringFundamentalsApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringFundamentalsApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(SpringFundamentalsApplication.class, args);
		System.out.println("Hello");
	}

	@Bean
	public CommandLineRunner demo(ApplicationRepository repository) {
		return (args) -> {
			repository.save(new Application("Trackzilla","kesha.williams","Application for tracking bugs."));
			repository.save(new Application("Expenses","mary.jones","Application to track expense reports."));
			repository.save(new Application("Notifications","karen.kane","Application to send alerts and notifications to users."));

			for (Application application : repository.findAll()) {
				log.info("The application is: " + application.toString());
			}
		};
	}

}
