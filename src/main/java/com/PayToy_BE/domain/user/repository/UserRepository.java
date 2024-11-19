package com.PayToy_BE.domain.user.repository;

import com.PayToy_BE.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByTel(String tel);
}
