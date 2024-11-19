package com.PayToy_BE.global.auth.Filter;

import com.PayToy_BE.domain.Session.entity.Session;
import com.PayToy_BE.domain.Session.repository.CustomSessionRepository;
import com.PayToy_BE.domain.user.dto.resopnse.UserLoginResponseDto;
import com.PayToy_BE.global.Session.utils.SessionUtils;
import com.PayToy_BE.global.auth.CustomUserDetails;
import com.PayToy_BE.global.exception.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.io.IOException;
import java.util.Map;

import static com.PayToy_BE.global.auth.Filter.SuccessMessage.*;
import static com.PayToy_BE.global.auth.exception.ErrorMessage.*;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AuthenticationManager authenticationManager;
    private final SessionUtils sessionUtils;
    private final CustomSessionRepository customSessionRepository;

    public LoginFilter(AuthenticationManager authenticationManager, SessionUtils sessionUtils, CustomSessionRepository customSessionRepository) {
        this.authenticationManager = authenticationManager;
        this.sessionUtils = sessionUtils;
        this.customSessionRepository = customSessionRepository;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String tel = null;
        String password = null;

        // JSON 요청 처리
        if (request.getContentType().equals("application/json")) {
            try {
                System.out.println("=====json ======");
                Map requestMap = objectMapper.readValue(request.getInputStream(), Map.class);
                tel = (String)requestMap.get("tel");
                password = (String)requestMap.get("password");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // 요청이 form-data 형식인경우 obtain으로 바로 꺼낼 수 있다.
            System.out.println("=====form-data======");
            tel = obtainUsername(request);
            password = obtainPassword(request);
        }
        log.info("tel:" + tel);
        log.info("password:" + password);

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(tel, password));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        // 유저 정보
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String tel = userDetails.getTel();
        Long userId = userDetails.getId();

        Session session = sessionUtils.createSessionId(tel);
        String sessionId = sessionUtils.addHeaderSessionId(response, session.getId());
        customSessionRepository.save(session);
        log.info("==========================");
        log.info(response.getHeader("Session"));

        response.setCharacterEncoding("UTF-8");      // 한글 설정
        response.setContentType("application/json"); // 응답을 JSON으로
        response.setStatus(LOGIN_SUCCESS.getStatus());

        response.getWriter().write(new ObjectMapper().writeValueAsString(ApiResponse.success(
                LOGIN_SUCCESS.getStatus(),
                LOGIN_SUCCESS.getCode(),
                LOGIN_SUCCESS.getMessage(),
                UserLoginResponseDto.from(userId, session.getId())
        )));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("unsuccessfulAuthentication 실행");

        response.setStatus(LOGIN_FAIL.getStatus());
        response.setCharacterEncoding("UTF-8");      // 한글 설정
        response.setContentType("application/json"); // 응답을 JSON으로

        response.getWriter().write(new ObjectMapper().writeValueAsString(ApiResponse.success(
                LOGIN_FAIL.getStatus(),
                LOGIN_FAIL.getCode(),
                LOGIN_FAIL.getMessage())));
    }
}