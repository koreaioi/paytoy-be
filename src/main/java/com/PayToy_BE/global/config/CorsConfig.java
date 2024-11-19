//package com.PayToy_BE.global.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//
//    @Value("${PUBLIC.IP.FRONT}") // 환경변수 없을 때 기본값 설정
//    private String friendIp;
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                //.allowedOrigins("http://paytoy-nginx:80/")
//                .allowedMethods("GET", "POST", "PUT", "PATCH", "OPTIONS")
//                .allowedHeaders("*")
//                //.allowCredentials(true) //.allowedOrigins("*")와 같이 사용할 수 없다. true시 도메인을 명시해줘야함.
//                .maxAge(3000);
//    }
//}
