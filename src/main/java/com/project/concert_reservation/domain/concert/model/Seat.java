package com.project.concert_reservation.domain.concert.model;

import com.project.concert_reservation.domain.concert.port.SeatRepository;
import com.project.concert_reservation.domain.concert.entity.SeatEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Seat {
    private Long id;

    private Long scheduleId;
    private Long price;

    private final SeatRepository seatRepository;

    public Optional<SeatEntity> getSeatById(Long id){
        return seatRepository.findSeatById(id);
    }

    public void addSeat(SeatEntity seatEntity){
        seatRepository.addSeat(seatEntity);
    }
}
