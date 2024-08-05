package com.project.concert_reservation.domain.concert.model;

import com.project.concert_reservation.domain.concert.entity.ReservationEntity;
import com.project.concert_reservation.domain.concert.entity.SeatEntity;
import com.project.concert_reservation.domain.concert.port.ReservationRepository;
import com.project.concert_reservation.domain.concert.port.SeatRepository;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

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
