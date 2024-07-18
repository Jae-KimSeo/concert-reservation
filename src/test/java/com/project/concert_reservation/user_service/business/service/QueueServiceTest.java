package com.project.concert_reservation.user_service.business.service;

import com.project.concert_reservation.ConcertReservationApplication;
import com.project.concert_reservation.user_service.business.domain.OngoingQueue;
import com.project.concert_reservation.user_service.business.domain.QueueType;
import com.project.concert_reservation.user_service.business.domain.WaitingQueue;
import com.project.concert_reservation.user_service.infrastructure.repository.QueueJpaRepository;
import com.project.concert_reservation.user_service.infrastructure.repository.QueueRepository;
import com.project.concert_reservation.user_service.mapper.QueueMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ConcertReservationApplication.class)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class QueueServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(QueueServiceTest.class);

    @Spy
    private WaitingQueue waitingQueue = new WaitingQueue(new HashMap<>());

    @Spy
    private OngoingQueue ongoingQueue= new OngoingQueue(100);

    @InjectMocks
    private QueueServiceImpl queueService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(waitingQueue.getQueue()).thenReturn(new LinkedList<>());
        when(ongoingQueue.getQueue()).thenReturn(new LinkedList<>());
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

        ongoingQueue.enqueueBatch(ongoingUserIds);
        waitingQueue.enqueueBatch(waitingUserIds);

        when(ongoingQueue.size()).thenReturn(90);
        when(waitingQueue.size()).thenReturn(10);

        queueService.RepositionQueue();

        assertTrue(waitingQueue.isEmpty());
        assertTrue(ongoingQueue.isFull());
    }

    @Test
    @DisplayName("큐 입장 테스트 : 해당 유저가 이미 존재하는 경우")
    public void testEnterQueue_UserAlreadyExists() {
        String userId = "user1";
        ongoingQueue.enqueue(userId);

        queueService.EnterQueue(userId);

        assertTrue(ongoingQueue.exists(userId), "User should already exist in waiting queue.");
        assertEquals(1, ongoingQueue.getSize(), "Queue size should not change.");
        logger.info("Test passed: User already exists in waiting queue.");
    }

    @Test
    @DisplayName("큐 입장 테스트 : 해당 유저가 존재하지 않는 경우")
    public void testEnterQueue_UserDoesNotExist() {
        String userId = "user2";
        queueService.EnterQueue(userId);

        assertTrue(ongoingQueue.exists(userId), "User should be added to ongoing queue.");
        assertEquals(1, ongoingQueue.getSize(), "Queue size should be 1 after adding new user.");
        logger.info("Test passed: User added to waiting queue.");
    }

    @Test
    @DisplayName("특정 유저의 큐 종류 확인 테스트")
    public void testCheckQueueTypeUserPositioned() {
        String userId = "user3";

        QueueType queueType = queueService.CheckQueueTypeUserPositioned(userId);

        assertEquals(QueueType.None, queueType, "QueueType should be None");
        logger.info("Test passed: QueueType for user is None.");
    }
}

