package com.project.concert_reservation.domain.concert.domain;

import com.project.concert_reservation.infra.concert.repository.SeatRepository;
import com.project.concert_reservation.domain.concert.entity.Seat;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class SeatDomain {
    private final SeatRepository seatRepository;

    public Optional<Seat> getSeatById(Long id){
        return seatRepository.findSeatById(id);
    }

    public void addSeat(Seat seat){
        seatRepository.addSeat(seat);
    }
}
