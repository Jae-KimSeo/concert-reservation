package com.project.concert_reservation.user_service.infrastructure.repository;

import com.project.concert_reservation.user_service.infrastructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    boolean existsById(Long id);
}
