package com.project.concert_reservation.reservation_service.Infrastructure.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long seatPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
