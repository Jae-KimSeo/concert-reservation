package com.project.concert_reservation.reservation_service.dto;

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
    private LocalDateTime holdDeadline;
    private String seatNo;
    private long price;
}