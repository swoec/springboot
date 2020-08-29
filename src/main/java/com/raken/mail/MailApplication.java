package com.raken.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }

}
