package com.project.concert_reservation.user_service.business.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueueServiceImpl implements QueueService {
    // 여기서 토큰이 들어나도 되긴함
    public String ValidateQueueStatus(String QueueStatus) {
        return "";
    }

    public String RequestWaitingToken(String UUID, int position) {
        return "jwt";
    }
}
