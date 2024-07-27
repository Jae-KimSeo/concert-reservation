package com.project.concert_reservation.infra.queue.repository;

import com.project.concert_reservation.domain.queue.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    User addUser(User user);
    Optional<User> findUserById(String id);
    boolean existsById(String id);
    void deleteAll();
}
