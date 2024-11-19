package com.PayToy_BE.domain.Session.repository;

import com.PayToy_BE.domain.Session.entity.Session;
import org.springframework.data.repository.CrudRepository;

public interface CustomSessionRepository extends CrudRepository<Session, String> {
}
