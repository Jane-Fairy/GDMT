package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author 13536
 */
@SpringBootApplication
@EnableConfigurationProperties(com.config.RsaKeyProperties.class)
@MapperScan("com.dao")
public class AdminGoodsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminGoodsApplication.class, args);
	}

}
