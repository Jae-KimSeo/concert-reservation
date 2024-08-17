package com.project.concert_reservation.interfaces.scheduler;

import com.project.concert_reservation.domain.queue.domain.QueueType;
import com.project.concert_reservation.domain.queue.entity.Queue;
import com.project.concert_reservation.infra.queue.repository.QueueRepository;
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
        List<Queue> queues = queueRepository.findQueueByUserId("");
        for (Queue queue : queues) {
            Duration duration = Duration.between(queue.getLastActiveTime(), LocalDateTime.now());
            if (duration.toMinutes() >= 3) {
                queue.setQueueType(QueueType.None);
                queueRepository.updateQueue(queue);
            }
        }
    }
}