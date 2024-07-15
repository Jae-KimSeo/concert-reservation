package com.project.concert_reservation.user_service.business.service;

import com.project.concert_reservation.user_service.business.domain.QueueType;

public interface QueueService {
    void EnterQueue(String userId);
    void RepositionQueue();
    QueueType CheckQueueTypeUserPositioned(String userId);
}
