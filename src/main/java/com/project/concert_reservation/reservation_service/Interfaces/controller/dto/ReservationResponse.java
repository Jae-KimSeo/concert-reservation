package com.project.concert_reservation.reservation_service.Interfaces.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class ReservationResponse {
    private boolean isReserved;
    private LocalDateTime reservedAt;
    private Long seatId;
    private Long seatPrice;
}