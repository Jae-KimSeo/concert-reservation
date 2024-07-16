package com.project.concert_reservation.user_service.infrastructure.repository;

import com.project.concert_reservation.exception.CustomException;
import com.project.concert_reservation.user_service.infrastructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
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

    public User findUserByUserId(Long id) {
        // TODO : Add CustomException case with Error Code
        return userJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException("UserID is not found in database", HttpStatus.NOT_FOUND));
    }

    public boolean existsByUserId(Long id) {
        return userJpaRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
        userJpaRepository.deleteAllInBatch();
    }
}
