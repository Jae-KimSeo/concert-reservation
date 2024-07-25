package com.project.concert_reservation.infra.concert.repository;

import com.project.concert_reservation.domain.concert.entity.Seat;

import java.util.List;
import java.util.Optional;

public interface SeatRepository {
    Seat addSeat(Seat seat);
    Optional<Seat> findSeatById(Long SeatId);
    List<Seat> findSeatByScheduleId(Long scheduleId);

}
