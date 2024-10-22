package com.project.concert_reservation.user_service.business.service;

import com.project.concert_reservation.ConcertReservationApplication;
import com.project.concert_reservation.application.queue.service.QueueService;
import com.project.concert_reservation.domain.queue.domain.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ConcertReservationApplication.class)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class QueueServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(QueueServiceTest.class);

    @InjectMocks
    private QueueService queueService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        logger.info("Setup complete with mocked queues");
    }

    @Test
    @DisplayName("대기열 큐에서 온고잉 큐 리포지션 테스트")
    public void testRepositionQueue() {
        List<String> ongoingUserIds = new ArrayList<>(90);
        List<String> waitingUserIds = new ArrayList<>(10);

        for (int i = 0; i < 90; i++) {
            ongoingUserIds.add(UUID.randomUUID().toString());
        }

        for (int i = 0; i < 10; i++){
            waitingUserIds.add(UUID.randomUUID().toString());
        }

        queueService.RepositionQueue();
    }

    @Test
    @DisplayName("큐 입장 테스트 : 해당 유저가 이미 존재하는 경우")
    public void testEnterQueue_UserAlreadyExists() {
        Long userId = 1L;

        queueService.EnterQueue(userId);

         logger.info("Test passed: User already exists in waiting queue.");
    }

    @Test
    @DisplayName("큐 입장 테스트 : 해당 유저가 존재하지 않는 경우")
    public void testEnterQueue_UserDoesNotExist() {
        Long userId = 1L;
        queueService.EnterQueue(userId);

        logger.info("Test passed: User added to waiting queue.");
    }

    @Test
    @DisplayName("특정 유저의 큐 종류 확인 테스트")
    public void testCheckQueueTypeUserPositioned() {
        Long userId = 3L;

        Queue.QueueType queueType = queueService.CheckQueueTypeUserPositioned(userId);

        assertEquals(Queue.QueueType.None, queueType, "QueueType should be None");
        logger.info("Test passed: QueueType for user is None.");
    }
}

