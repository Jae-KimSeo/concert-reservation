package com.project.concert_reservation.user_service.infrastructure.repository;

import com.project.concert_reservation.exception.CustomException;
import com.project.concert_reservation.user_service.infrastructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User addUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return userJpaRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll();
    }

    public Optional<User> findUserByUserId(String id) {
        // TODO : Add CustomException case with Error Code
        return userJpaRepository.findById(id);
    }

    public boolean existsByUserId(String id) {
        return userJpaRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
        userJpaRepository.deleteAllInBatch();
    }
}
