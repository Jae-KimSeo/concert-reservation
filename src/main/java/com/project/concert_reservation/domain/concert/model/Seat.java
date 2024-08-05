package com.project.concert_reservation.domain.concert.model;

import com.project.concert_reservation.domain.concert.port.SeatRepository;
import com.project.concert_reservation.domain.concert.entity.SeatEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Setter
@Getter
public class Seat {
    private Long id;
    private Long scheduleId;
    private Long price;
}
