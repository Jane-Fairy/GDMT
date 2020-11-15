package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    //配置了Swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment){
        //获取项目的环境:
        Profiles profiles = Profiles.of("dev");
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)//enable是否要启动wagger,如果为False,则swagger不能测试
                .select()
                        //RequestHandlerSelectors配置要扫描接口的方式
                        //basePackage指定要扫描的包
                        //any()扫描全部
                        //none()不扫描
                        //withClassAnnotation()扫描类上的注解
                        //withMethodAnnotation()扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.controller"))
                .build();
    }

    public ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("彭小川","https://baidu.com","765403293@qq.com");
        return new ApiInfo(
                "彭小川的SwaggerAPI文档",
                "人生在勤,不索何获",
                "v1.0",
                "https://www.baidu.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()

        );
    }

}
