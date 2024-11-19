package com.PayToy_BE.global.Session.utils;

import com.PayToy_BE.domain.Session.entity.Session;
import com.PayToy_BE.domain.user.entity.User;
import com.PayToy_BE.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static com.PayToy_BE.global.auth.exception.ErrorMessage.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class SessionUtils {

    private final RedisTemplate<String, Object> redisTemplate;
    private final UserRepository userRepository;

    public Session createSessionId(String telephone){
        String uuid = UUID.randomUUID().toString();

        return new Session(uuid, Integer.valueOf(telephone));
    }

    // access를 헤더에 실는다.
    public String addHeaderSessionId(HttpServletResponse response, String sessionId) {

//        response.setHeader("Access-Control-Expose-Headers", "Session");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Session", sessionId);
//        log.info("session_id 헤더에 추가 완료");
//        log.info(response.getHeader("Session"));
//        log.info("======================");


        response.setHeader("Session", sessionId);
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Expose-Headers", "Session");  // 여기 추가

        log.info("Session Header set: " + sessionId);
        log.info("======================");
        return sessionId;
    }

    public Optional<User> getUsereBySessionId(String sessionId){
        String key = "session:" + sessionId;
        String value = String.valueOf(redisTemplate.opsForHash().get(key, "value"));
        log.info("Redis 세션에서 확인: "+value);

        return userRepository.findByTel("0" + value);
    }

    public boolean validateSessionId(String sessionId, HttpServletRequest request){
        if(sessionId == null){
            request.setAttribute("errormessage", SESSION_ID_ISNULL);
            return false;
        }
        return true;
    }


}