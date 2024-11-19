package com.PayToy_BE.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${PUBLIC.IP.FRONT}") // 환경변수 없을 때 기본값 설정
    private String friendIp;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 엔드포인트에 대해 CORS 허용
                .allowedOriginPatterns("*")
//                .allowedOrigins("http://localhost:3000", "http://paytoy-fe:3000",friendIp) // 프론트엔드 도메인
                .allowedHeaders("*")
                .allowedMethods("GET","PATCH", "POST", "PUT", "DELETE", "HEAD", "OPTIONS") // 허용할 HTTP 메서드
                .exposedHeaders("Session")
                .allowCredentials(true); // 쿠키 전송을 허용하는 경우
    }
}