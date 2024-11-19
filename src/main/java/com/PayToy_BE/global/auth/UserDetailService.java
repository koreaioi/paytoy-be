package com.PayToy_BE.global.auth;

import com.PayToy_BE.domain.user.entity.User;
import com.PayToy_BE.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String tel) throws UsernameNotFoundException {

        User user = userRepository.findByTel(tel)
                .orElse(null);

        if (user != null) {
            return new CustomUserDetails(user);
        }else {
            log.info("Cannot find User");
        }

        return null;
    }
}