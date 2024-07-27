package com.project.concert_reservation.application.queue.service;

import com.project.concert_reservation.domain.queue.domain.OngoingQueue;
import com.project.concert_reservation.domain.queue.domain.WaitingQueue;
import com.project.concert_reservation.domain.queue.domain.QueueType;
import com.project.concert_reservation.domain.queue.entity.Queue;
import com.project.concert_reservation.infra.queue.repository.QueueRepository;
import com.project.concert_reservation.mapper.queue.QueueMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class QueueServiceImpl implements QueueService {
    private final OngoingQueue ongoingQueue;
    private final WaitingQueue waitingQueue;
    private final QueueMapper queueMapper;
    private static final Logger logger = LoggerFactory.getLogger(QueueServiceImpl.class);

    @Autowired
    private QueueRepository queueRepository;

    public void EnterQueue(String userId){
        if (ongoingQueue.getQueue().size() == ongoingQueue.getCapacity()){
            Queue queue = new Queue();
            queue.setQueueType(QueueType.WAITING);
            queue.setEnterAt(LocalDateTime.now());
            queue.setUserId(userId);
            Queue savedQueue = queueRepository.addQueue(queue);
            waitingQueue.enqueue(userId, savedQueue.getId());
        } else {
            Queue queue = new Queue();
            queue.setQueueType(QueueType.ONGOING);
            queue.setEnterAt(LocalDateTime.now());
            queue.setUserId(userId);
            queueRepository.addQueue(queue);
            ongoingQueue.enqueue(userId);
        }
    }
    public void RepositionQueue(){
        var leftActiveCount = ongoingQueue.getCapacity() - ongoingQueue.size();
        if (leftActiveCount > 0) {
            var repositionUserIds = waitingQueue.dequeueBatch(leftActiveCount);
            ongoingQueue.enqueueBatch(repositionUserIds);
        } else {
            logger.info("There is no left capacity in ongoing queue");
        }
    }

    public Long GetUserLeftWaitingCount(String userId){
        Long queueId, firstWaitingUserQueueId;

        List<Queue> queues = queueRepository.findQueueByUserId(userId);
        if (queues.size() == 1) {
            queueId = queues.getFirst().getId();
        } else {
            return -1L;
        }
        List<Queue> firstWaitingQueues = queueRepository.findQueueByUserId(waitingQueue.getQueue().peekFirst());
        if (firstWaitingQueues.size() == 1) {
            firstWaitingUserQueueId = firstWaitingQueues.getFirst().getId();
        } else {
            return -1L;
        }
        return queueId - firstWaitingUserQueueId;
    }

    public QueueType CheckQueueTypeUserPositioned(String userId){
        List<Queue> queues = queueRepository.findQueueByUserId(userId);
        if (queues.isEmpty()) {
            return QueueType.None;
        } else {
            return queues.getFirst().getQueueType();       }
    }
}
