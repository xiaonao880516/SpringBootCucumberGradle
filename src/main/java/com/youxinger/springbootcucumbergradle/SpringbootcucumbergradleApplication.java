package com.youxinger.springbootcucumbergradle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootcucumbergradleApplication {


    private static final Logger logger = LoggerFactory.getLogger(SpringbootcucumbergradleApplication.class);

    public static void main(String[] args) {

        logger.info("SpringBoot开始加载");
        SpringApplication.run(SpringbootcucumbergradleApplication.class, args);
        logger.info("SpringBoot加载完毕");
    }

}
