package com.project.concert_reservation.user_service.business.service;

import com.project.concert_reservation.user_service.business.model.Queue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final Queue ongoingQueue;
    private final Queue waitingQueue;

    //0. 대기열 큐 생성, 온고잉 큐 생성
    //1. 유저 존재 여부 확인(DB)
    //2. 토큰 발급 여부 확인(DB)
    //3. 큐 상태 확인
    //4. 토큰 요청 & 큐 진입
    //서비스 라이프사이클 있어야하나 -> 큐제거등

    public String ValidateUser(String UUID) {
        return "userId";
    }
}
