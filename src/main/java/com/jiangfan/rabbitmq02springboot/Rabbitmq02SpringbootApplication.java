package com.jiangfan.rabbitmq02springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:spring-rabbitmq.xml"})
@SpringBootApplication
public class Rabbitmq02SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(Rabbitmq02SpringbootApplication.class, args);
    }

}
