package com.project.concert_reservation.reservation_service.Infrastructure.Repository;

import com.project.concert_reservation.reservation_service.Infrastructure.entity.Seat;

import java.util.List;
import java.util.Optional;

public interface SeatRepository {
    Seat addSeat(Seat seat);
    Optional<Seat> findSeatById(Long SeatId);
    List<Seat> findSeatByScheduleId(Long scheduleId);

}
