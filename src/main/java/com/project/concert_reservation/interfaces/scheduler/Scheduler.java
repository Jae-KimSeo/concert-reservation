package com.project.concert_reservation.interfaces.scheduler;

import com.project.concert_reservation.domain.queue.domain.Queue;
import com.project.concert_reservation.domain.queue.entity.QueueEntity;
import com.project.concert_reservation.domain.queue.port.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Scheduler {

    private final QueueRepository queueRepository;

    @Scheduled(fixedRate = 60000)
    public void checkDatabaseField() {
        List<QueueEntity> queueEntities = queueRepository.findQueueByUserId(1L);
        for (QueueEntity queueEntity : queueEntities) {
            Duration duration = Duration.between(queueEntity.getLastActiveTime(), LocalDateTime.now());
            if (duration.toMinutes() >= 3) {
                queueEntity.setQueueType(Queue.QueueType.None);
                queueRepository.updateQueue(queueEntity);
            }
        }
    }
}