package com.project.concert_reservation.interfaces.concert.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ReservationRequest {
    // TODO : userId come from jwt
    private Long userId;
    private Long scheduleId;
    private Long seatId;
}