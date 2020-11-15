package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(com.config.RsaKeyProperties.class)
public class GoodsManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsManageApplication.class, args);
    }

}
