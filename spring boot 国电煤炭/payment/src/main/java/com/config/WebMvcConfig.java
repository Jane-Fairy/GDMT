package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedHeaders("Content-Type","X-Requested-With","accept,Origin","Access-Control-Request-Method","Access-Control-Request-Headers","token")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowCredentials(true);
    }

//    @Bean
//    public CorsFilter corsFilter() {
//        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        final CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true); /*是否允许请求带有验证信息*/
//        corsConfiguration.addAllowedOrigin("*");/*允许访问的客户端域名*/
//        corsConfiguration.addAllowedHeader("*");/*允许服务端访问的客户端请求头*/
//        corsConfiguration.addAllowedMethod("*"); /*允许访问的方法名,GET POST等*/
////        corsConfiguration.addExposedHeader("token");/*暴露哪些头部信息 不能用*因为跨域访问默认不能获取全部头部信息*/
////        corsConfiguration.addExposedHeader("TOKEN");
//        corsConfiguration.addExposedHeader("Auth");
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//        return new CorsFilter(urlBasedCorsConfigurationSource);
//    }

}
