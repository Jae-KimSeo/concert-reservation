package com.project.concert_reservation.user_service.business.domain;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class WaitingQueueTest {
    @Mock
    WaitingQueue waitingQueue;
    private static final Logger logger = LoggerFactory.getLogger(WaitingQueueTest.class);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("대기열큐 유저 삽입 테스트")
    public void enqueue() {
        String userId = UUID.randomUUID().toString();

        waitingQueue.enqueue(userId);

        String lastElement = waitingQueue.getQueue().peekLast();
        assertEquals(userId, lastElement);
        if (userId.equals(lastElement)) {
            logger.info("Success: userId is correctly added to the queue.");
        }

        String randomUserId = UUID.randomUUID().toString();
        assertNotEquals(randomUserId, lastElement);
        if (!randomUserId.equals(lastElement)) {
            logger.info("Success: randomUserId is not equal to the last element in the queue.");
        }
    }

    @Test
    @DisplayName("대기열큐 배치 제거 테스트")
    public void dequeueBatchTest() {
        for (int i = 0; i <10; i++) {
            waitingQueue.enqueue(UUID.randomUUID().toString());
        }

        when(waitingQueue.size()).thenReturn(10);

        waitingQueue.dequeueBatch(10);

        assertFalse(waitingQueue.isEmpty());
        logger.info("Success : dequeue batch is successfully empty the queue");
    }
}
