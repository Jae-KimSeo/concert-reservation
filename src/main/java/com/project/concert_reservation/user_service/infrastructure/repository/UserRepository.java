package com.project.concert_reservation.user_service.infrastructure.repository;

import com.project.concert_reservation.user_service.infrastructure.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    User addUser(User user);
    Optional<User> findUserByUserId(String id);
    boolean existsByUserId(String id);
    void deleteAll();
}
