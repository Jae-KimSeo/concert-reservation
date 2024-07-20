package com.project.concert_reservation.infra.queue.repository;

import com.project.concert_reservation.domain.queue.entity.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueueJpaRepository extends JpaRepository<Queue, Long> {
    List<Queue> findByUserId(String userId);
    List<Queue> findQueueByQueueType(QueueType queueType);
    void deleteByUserId(String userId);
}
