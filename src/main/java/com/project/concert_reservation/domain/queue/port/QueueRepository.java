package com.project.concert_reservation.domain.queue.port;

import com.project.concert_reservation.domain.queue.entity.QueueEntity;

public interface QueueRepository {
    void enterQueue(QueueEntity queueEntity);
    Integer getQueueRankById(Long userId);
    void activateQueueTokens();
    void expireActivateToken(Long userId);
    void expireUnActivatedTokens();
}
