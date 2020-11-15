package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Harlan
 */
@SpringBootApplication
@MapperScan("com.dao")
@EnableConfigurationProperties(com.config.RsaKeyProperties.class)
public class AdminRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminRegisterApplication.class, args);
	}

}
