package com.project.concert_reservation.application.queue.service;

import com.project.concert_reservation.domain.queue.domain.QueueType;

public interface QueueService {
    void EnterQueue(String userId);
    void RepositionQueue();

    Long GetUserLeftWaitingCount(String userId);
    // 근데 실질적으로 QueueType 을 DB에 저장할 수 있나?
    // 이거 큐 맨앞애랑 시간 비교로 가는게 맞을듯
    // 만료됬을 경우는 Queue Table 제거
    // QueueType 을 큐 테이블에서는 저장, 유저 테이블에서는 저장 하지 않음
    QueueType CheckQueueTypeUserPositioned(String userId);
}
