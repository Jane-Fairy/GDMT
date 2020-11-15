package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author 寰
 * @create 2020-10-30-9:22
 */
@Configuration //配置类
@EnableSwagger2// 开启Swagger2的自动配置
public class Swagger {

    //配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("联系人名字", "http://xxx.xxx.com/联系人访问链接", "联系人邮箱");
        return new ApiInfo(
                "Swagger学习", // 标题
                "学习演示如何配置Swagger", // 描述
                "v1.0", // 版本
                "http://terms.service.url/组织链接", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("陈酱")
                .apiInfo(apiInfo())
                .enable(true)//enable是否启动swagger，如果为false，则swagger不能再浏览器中访问
                .select()
                //RequestHandlerSelectors配置要扫描接口的方式
                //basePackage指定要扫描的包
                //any():扫描全部
                //none():不扫描
                //withClassAnnotation:扫描类上的注解，参数是一个注解的反射对象(RestController.class)
                //withMethodAnnotation:扫描方法上的注解
                .apis(RequestHandlerSelectors.any())
                //paths()过滤什么路径，配置了就只扫描不过滤的
                        .paths(PathSelectors.ant("/goods/**"))
                .build()
                ;
    }
}
