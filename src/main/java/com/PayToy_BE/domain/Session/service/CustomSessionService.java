package com.PayToy_BE.domain.Session.service;

import com.PayToy_BE.domain.Session.entity.Session;
import com.PayToy_BE.domain.Session.repository.CustomSessionRepository;
import com.PayToy_BE.global.Session.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomSessionService {

    private final CustomSessionRepository customSessionRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final SessionUtils sessionUtils;


    public void getSessionValue(String sessionId) {

        String value = getValueById(sessionId);

        System.out.println(value);
    }

    public String getValueById(String id) {
        // Redis의 key 패턴에 맞추어 full key 생성
        String key = "session:" + id;
        // key에서 'value' 필드를 가져옴
        return String.valueOf(redisTemplate.opsForHash().get(key, "value"));
    }

    public void saveSession(String tel){
        Session session = sessionUtils.createSessionId(tel);
        customSessionRepository.save(session);
    }


}
