package com.project.concert_reservation.user_service.infrastructure.repository;

import com.project.concert_reservation.user_service.infrastructure.entity.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueueJpaRepository extends JpaRepository<Queue, Long> {
    List<Queue> findByUserId(String userId);
    void deleteByUserId(String userId);
}
