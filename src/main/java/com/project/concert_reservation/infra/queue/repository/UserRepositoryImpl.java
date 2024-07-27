package com.project.concert_reservation.infra.queue.repository;

import com.project.concert_reservation.domain.queue.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public User addUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return userJpaRepository.save(user);
    }

    public List<User> findAll() {
        return userJpaRepository.findAll();
    }

    public Optional<User> findUserById(String id) {
        // TODO : Add CustomException case with Error Code
        return userJpaRepository.findById(id);
    }

    public boolean existsById(String id) {
        return userJpaRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
        userJpaRepository.deleteAllInBatch();
    }
}
