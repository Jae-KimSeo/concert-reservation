package com.project.concert_reservation.infra.queue.repository;

import com.project.concert_reservation.domain.queue.entity.UserEntity;
import com.project.concert_reservation.domain.queue.port.UserRepository;
import com.project.concert_reservation.infra.queue.repository.orm.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserEntity addUser(UserEntity userEntity) {
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());

        return userJpaRepository.save(userEntity);
    }

    public List<UserEntity> findAll() {
        return userJpaRepository.findAll();
    }

    public Optional<UserEntity> findUserById(Long id) {
        // TODO : Add CustomException case with Error Code
        return userJpaRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return userJpaRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
        userJpaRepository.deleteAllInBatch();
    }
}
