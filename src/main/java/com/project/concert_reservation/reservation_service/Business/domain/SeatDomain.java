package com.project.concert_reservation.reservation_service.Business.domain;

import com.project.concert_reservation.reservation_service.Infrastructure.Repository.SeatRepository;
import com.project.concert_reservation.reservation_service.Infrastructure.entity.Seat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
