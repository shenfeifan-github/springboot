package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SprongbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprongbootApplication.class, args);
    }

}
