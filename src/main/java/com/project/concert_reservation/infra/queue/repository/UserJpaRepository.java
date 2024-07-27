package com.project.concert_reservation.infra.queue.repository;

import com.project.concert_reservation.domain.queue.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, String> {
}
