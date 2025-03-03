package com.project.concert_reservation.user_service.infrastructure.repository;

import com.project.concert_reservation.user_service.business.domain.QueueType;
import com.project.concert_reservation.user_service.infrastructure.entity.Queue;

import java.util.List;

public interface QueueRepository {
    Queue addQueue(Queue queue);
    Queue updateQueue(Queue queue);
    List<Queue> findQueueByUserId(String userId);
    List<Queue> findQueueByQueueType(QueueType queueType);
    void deleteQueueByUserID(String userId);
}
