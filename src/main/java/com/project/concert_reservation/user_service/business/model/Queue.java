package com.project.concert_reservation.user_service.business.model;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Queue {
    private QueueType queueType;
    private int capacity;
    private int currentElementNum;

    //이거 3개 QueueService로 가야하나?
    void CreateQueue(QueueType queueType, int capacity) {
    }

    QueueStatus CheckQueueStatus(QueueType queueType) {
        return QueueStatus.AVAILABLE;
    }

    void EnterQueue(QueueType queueType, String UUID) {

    }
}
