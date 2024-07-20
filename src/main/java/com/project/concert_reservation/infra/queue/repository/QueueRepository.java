package com.project.concert_reservation.infra.queue.repository;

import com.project.concert_reservation.domain.queue.entity.Queue;
import java.util.List;

public interface QueueRepository {
    Queue addQueue(Queue queue);
    Queue updateQueue(Queue queue);
    List<Queue> findQueueByUserId(String userId);
    List<Queue> findQueueByQueueType(QueueType queueType);
    void deleteQueueByUserID(String userId);
}
