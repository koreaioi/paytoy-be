package com.PayToy_BE.global.auth.entrypoint;

import com.PayToy_BE.global.auth.exception.ErrorMessage;
import com.PayToy_BE.global.exception.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import java.io.IOException;


@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorMessage error = (ErrorMessage) request.getAttribute("errormessage");
        response.setStatus(error.getStatus()); // 401 상태 반환
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(new ObjectMapper().writeValueAsString(ApiResponse.fail(
                error.getStatus(),
                error.getCode(),
                error.getMessage()
        )));
    }
}