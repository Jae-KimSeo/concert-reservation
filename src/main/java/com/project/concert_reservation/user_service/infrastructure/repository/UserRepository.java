package com.project.concert_reservation.user_service.infrastructure.repository;

import com.project.concert_reservation.user_service.infrastructure.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User addUser(User user);
    User findUserByUserId(Long id);
    boolean existsByUserId(Long id);
    void deleteAll();
}
