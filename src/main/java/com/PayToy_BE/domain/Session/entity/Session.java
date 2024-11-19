package com.PayToy_BE.domain.Session.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@ToString
@RedisHash(value="session", timeToLive = 60* 10L)
public class Session {

    @Id
    private final String id;
    private final Integer value;

    public Session(String id, Integer value) {
        this.id = "s"+id;
        this.value = value;
    }
}
