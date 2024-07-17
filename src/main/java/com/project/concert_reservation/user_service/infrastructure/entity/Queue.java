package com.project.concert_reservation.user_service.infrastructure.entity;

import com.project.concert_reservation.user_service.business.domain.QueueType;
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
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private QueueType queueType;
    private LocalDateTime enterAt;
    private LocalDateTime lastActiveTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
