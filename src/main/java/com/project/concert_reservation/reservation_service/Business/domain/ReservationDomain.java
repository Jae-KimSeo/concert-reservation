package com.project.concert_reservation.reservation_service.Business.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationDomain {
    private Long id;
    private String holderId;
    private SeatDomain seatDomain;
    private LocalDateTime reservedAt;
    private LocalDateTime paidAt;
}
