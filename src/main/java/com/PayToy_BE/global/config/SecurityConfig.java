package com.PayToy_BE.global.config;

import com.PayToy_BE.domain.Session.repository.CustomSessionRepository;
import com.PayToy_BE.global.Session.utils.SessionUtils;
import com.PayToy_BE.global.auth.Filter.LoginFilter;
import com.PayToy_BE.global.auth.Filter.SessionFilter;
import com.PayToy_BE.global.auth.entrypoint.CustomAuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final CustomSessionRepository customSessionRepository;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final SessionUtils sessionUtils;

    @Value("${PUBLIC.IP.FRONT}") // 환경변수 없을 때 기본값 설정
    private String friendIp;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()));

        //csrf 비활성화
        http.csrf((auth) -> auth.disable());
        //Spring Security의 Default 로그인 방식 - From 로그인 방식 비활성화
        http.formLogin((auth) -> auth.disable());
        //http basic 인증 방식 disable
        http.httpBasic((auth) -> auth.disable());

        http.authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login", "/", "/user", "/health-check", "/sessionid", "/user/tel/**").permitAll()
                        .anyRequest().authenticated());

        //SessionFilter 등록
        http.addFilterBefore(new SessionFilter(sessionUtils), LoginFilter.class);
        // LoginFilter 등록
        LoginFilter loginFilter = new LoginFilter(authenticationManager(authenticationConfiguration), sessionUtils, customSessionRepository);
        loginFilter.setUsernameParameter("tel");
        http.addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class);
        // customAuthenticationEntryPoint 등록
        http.exceptionHandling(e -> e.authenticationEntryPoint(customAuthenticationEntryPoint));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOriginPatterns(List.of("*"));
//        corsConfiguration.setAllowedOrigins(List.of(friendIp, "http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of("GET","PATCH", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type","Session", "session_id"));
        corsConfiguration.setExposedHeaders(List.of("Session"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}
