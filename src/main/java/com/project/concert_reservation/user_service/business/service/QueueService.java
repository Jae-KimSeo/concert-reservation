package com.project.concert_reservation.user_service.business.service;

public interface QueueService {
    String ValidateQueueStatus(String QueueStatus);
    String RequestWaitingToken(String UUID, int position);
}
