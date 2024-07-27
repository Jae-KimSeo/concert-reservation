package com.project.concert_reservation.interfaces.concert.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ReservationRequest {
    private Long scheduleId;
    private Long seatId;
}