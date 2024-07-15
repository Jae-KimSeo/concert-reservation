package com.project.concert_reservation.user_service.infrastructure.entity;

import com.project.concert_reservation.user_service.business.domain.QueueType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // use autoincrement for identify

    private String userId;
    private QueueType queueType;
    private LocalDateTime enterOngoingAt;
    private LocalDateTime lastActiveTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
