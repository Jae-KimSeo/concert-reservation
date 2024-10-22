package com.project.concert_reservation.domain.concert.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@Component
public class Reservation {
    private Long id;
    private Long holderId;
    private Long seatId;
    private boolean isReserved = false;
    private LocalDateTime reservedAt;
    private LocalDateTime paidAt;
}
