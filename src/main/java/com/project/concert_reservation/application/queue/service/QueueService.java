package com.project.concert_reservation.application.queue.service;

import com.project.concert_reservation.domain.queue.domain.Queue;
import com.project.concert_reservation.domain.queue.entity.QueueEntity;
import com.project.concert_reservation.domain.queue.port.QueueRepository;
import com.project.concert_reservation.mapper.queue.QueueMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class QueueService {
    private final QueueMapper queueMapper;
    private static final Logger logger = LoggerFactory.getLogger(QueueService.class);

    private QueueRepository queueRepository;

    public void EnterQueue(Long userId){


        // TODO : Do it same thing in redis set
//        if (ongoingQueue.getQueue().size() == ongoingQueue.getCapacity()){
//            QueueEntity queueEntity = new QueueEntity();
//            queueEntity.setQueueType(Queue.QueueType.WAITING);
//            queueEntity.setEnterAt(LocalDateTime.now());
//            queueEntity.setUserId(userId);
//            QueueEntity savedQueueEntity = queueRepository.addQueue(queueEntity);
//            waitingQueue.enqueue(userId, savedQueueEntity.getId());
//        } else {
//            QueueEntity queueEntity = new QueueEntity();
//            queueEntity.setQueueType(Queue.QueueType.ONGOING);
//            queueEntity.setEnterAt(LocalDateTime.now());
//            queueEntity.setUserId(userId);
//            queueRepository.addQueue(queueEntity);
//            ongoingQueue.enqueue(userId);
//        }
    }
    public void RepositionQueue(){
    }

    public Long GetUserLeftWaitingCount(Long userId){
        Long queueId, firstWaitingUserQueueId;

//        List<QueueEntity> queueEntities = queueRepository.findQueueByUserId(userId);
//        if (queueEntities.size() == 1) {
//            queueId = queueEntities.getFirst().getId();
//        } else {
//            return -1L;
//        }
//        List<QueueEntity> firstWaitingQueueEntities = queueRepository.findQueueByUserId(waitingQueue.getQueue().peekFirst());
//        if (firstWaitingQueueEntities.size() == 1) {
//            firstWaitingUserQueueId = firstWaitingQueueEntities.getFirst().getId();
//        } else {
//            return -1L;
//        }
        //return queueId - firstWaitingUserQueueId;
        return 1L;
    }

    public Queue.QueueType CheckQueueTypeUserPositioned(Long userId){
        return Queue.QueueType.None;
    }
}
