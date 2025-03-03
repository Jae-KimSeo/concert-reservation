package com.project.concert_reservation.domain.queue.port;

import com.project.concert_reservation.domain.queue.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<UserEntity> findAll();
    UserEntity addUser(UserEntity userEntity);
    Optional<UserEntity> findUserById(Long id);
    boolean existsById(Long id);
    void deleteAll();
}
