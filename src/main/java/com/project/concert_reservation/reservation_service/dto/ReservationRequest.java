package com.project.concert_reservation.reservation_service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ReservationRequest {
    private String scheduleId;
    private String seatId;
}