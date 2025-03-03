package com.project.concert_reservation.user_service.interfaces.scheduler;

import com.project.concert_reservation.user_service.business.domain.QueueType;
import com.project.concert_reservation.user_service.infrastructure.entity.Queue;
import com.project.concert_reservation.user_service.infrastructure.repository.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
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
        List<Queue> queues = queueRepository.findQueueByQueueType(QueueType.ONGOING);
        for (Queue queue : queues) {
            Duration duration = Duration.between(queue.getLastActiveTime(), LocalDateTime.now());
            if (duration.toMinutes() >= 3) {
                queue.setQueueType(QueueType.None);
                queueRepository.updateQueue(queue);
            }
        }
    }
}