package com.project.concert_reservation.infra.queue.repository.orm;

import com.project.concert_reservation.domain.queue.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
