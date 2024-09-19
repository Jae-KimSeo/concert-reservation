package com.project.concert_reservation.domain.queue.entity;

import com.project.concert_reservation.domain.queue.domain.Queue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class QueueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Queue.QueueType queueType;
    private LocalDateTime enterAt;
    private LocalDateTime lastActiveTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
