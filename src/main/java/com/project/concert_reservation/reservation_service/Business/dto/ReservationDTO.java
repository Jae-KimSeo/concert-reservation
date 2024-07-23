package com.project.concert_reservation.reservation_service.Business.dto;

import com.project.concert_reservation.reservation_service.Infrastructure.entity.Seat;
import jakarta.persistence.OneToOne;
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
