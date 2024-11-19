package com.PayToy_BE.domain.user.service;

import com.PayToy_BE.domain.user.dto.request.UserRegisterRequestDto;
import com.PayToy_BE.domain.user.entity.User;
import com.PayToy_BE.domain.user.exception.UserErrorException.*;
import com.PayToy_BE.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public void saveUser(UserRegisterRequestDto requestDto) {
        validateTel(requestDto.tel());
        String encodePassword = encoder.encode(requestDto.password());
        userRepository.save(User.of(requestDto,encodePassword));
    }

    public void validateTel(String tel) {
        userRepository.findByTel(tel).ifPresent(user -> {
            throw new TelDuplicateException();
        });
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }
}
