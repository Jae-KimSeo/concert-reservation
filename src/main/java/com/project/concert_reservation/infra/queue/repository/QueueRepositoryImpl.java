package com.project.concert_reservation.infra.queue.repository;

import com.project.concert_reservation.domain.queue.entity.Queue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class QueueRepositoryImpl implements QueueRepository {

    private final QueueJpaRepository queueJpaRepository;

    public Queue addQueue(Queue queue){
        queue.setCreatedAt(LocalDateTime.now());
        queue.setUpdatedAt(LocalDateTime.now());

        return queueJpaRepository.save(queue);
    }
    public Queue updateQueue(Queue queue){
        queue.setUpdatedAt(LocalDateTime.now());

        return queueJpaRepository.save(queue);
    }
    public List<Queue> findQueueByUserId(String userId){
       return queueJpaRepository.findByUserId(userId);
    }
    public void deleteQueueByUserID(String userId){
        queueJpaRepository.deleteByUserId(userId);
    }
}
