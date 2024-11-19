package com.PayToy_BE.global.auth.Filter;

import com.PayToy_BE.domain.user.entity.User;
import com.PayToy_BE.global.Session.utils.SessionUtils;
import com.PayToy_BE.global.auth.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

import static com.PayToy_BE.global.auth.exception.ErrorMessage.*;

@Slf4j
public class SessionFilter extends OncePerRequestFilter {

    private final static String LOGIN_URL = "/login";
    private final SessionUtils sessionUtils;

    public SessionFilter(SessionUtils sessionUtils) {
        this.sessionUtils = sessionUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("SessionFilter 실행");

        // URL이 /login인 경우 토큰 검증 건너뛰기
        if (request.getRequestURI().equals(LOGIN_URL)) {
            filterChain.doFilter(request, response);
            return;
        }

        String sessionId = request.getHeader("Session");
        log.info("Session: {}", sessionId);

        if (!sessionUtils.validateSessionId(sessionId, request)) {
             filterChain.doFilter(request, response);
             return;
        }


        Optional<User> findUser = sessionUtils.getUsereBySessionId(sessionId);
        if(findUser.isEmpty()){
            request.setAttribute("errormessage", SESSION_ID_INVALID);
            filterChain.doFilter(request, response);
            return;
        }

        CustomUserDetails customUserDetails = new CustomUserDetails(findUser.get());
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
