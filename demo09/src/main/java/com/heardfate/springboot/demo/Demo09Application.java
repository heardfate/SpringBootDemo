package com.heardfate.springboot.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo09Application {

    private static final Logger logger = LoggerFactory.getLogger(Demo09Application.class);

    public static void main(String[] args) {
        logger.debug("App Will start");
        SpringApplication.run(Demo09Application.class, args);
        logger.info("App start over");
    }
}
