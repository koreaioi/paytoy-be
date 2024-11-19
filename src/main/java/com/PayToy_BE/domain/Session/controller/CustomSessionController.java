package com.PayToy_BE.domain.Session.controller;

import com.PayToy_BE.domain.Session.service.CustomSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomSessionController {

    private final CustomSessionService customSessionService;

    // 테스트 세션 컨트롤러
    @PostMapping("/session/{tel}")
    public void createSession(@PathVariable String tel) {
        customSessionService.saveSession(tel);
    }

}
