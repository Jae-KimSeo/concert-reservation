package com.project.concert_reservation.user_service.business.service;

import com.project.concert_reservation.user_service.business.domain.OngoingQueue;
import com.project.concert_reservation.user_service.business.domain.WaitingQueue;
import com.project.concert_reservation.user_service.business.domain.QueueType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class QueueServiceImpl implements QueueService {
    private final OngoingQueue ongoingQueue;
    private final WaitingQueue waitingQueue;
    private static final Logger logger = LoggerFactory.getLogger(QueueServiceImpl.class);

    public void EnterQueue(String userId){
        if (ongoingQueue.getQueue().size() == ongoingQueue.getCapacity()){
            //TODO : Set Position from DB primary key autoincrement
            waitingQueue.enqueue(userId, 1L);
        } else {
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
    public QueueType CheckQueueTypeUserPositioned(String userId){
        // TODO : Check user queue type from queue table
        return QueueType.None;
    }
}
