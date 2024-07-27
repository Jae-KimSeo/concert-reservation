package com.project.concert_reservation.domain.concert.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationDTO {
    private Long id;

    private String holderId;

    private SeatDTO seatDTO;

    private LocalDateTime reservedAt;
    private LocalDateTime paidAt;
}
