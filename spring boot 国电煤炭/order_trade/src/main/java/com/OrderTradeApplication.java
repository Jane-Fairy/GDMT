package com;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableRabbit
@EnableConfigurationProperties(com.config.RsaKeyProperties.class)
public class OrderTradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderTradeApplication.class,args);
    }
}
