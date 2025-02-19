package com.project.concert_reservation.reservation_service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class ReservationSeatsResponse {
    private List<String> seatIds;
    private List<String> seatNos;
}
