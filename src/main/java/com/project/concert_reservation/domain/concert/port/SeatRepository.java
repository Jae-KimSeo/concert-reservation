package com.project.concert_reservation.domain.concert.port;

import com.project.concert_reservation.domain.concert.entity.SeatEntity;

import java.util.List;
import java.util.Optional;

public interface SeatRepository {
    SeatEntity addSeat(SeatEntity seatEntity);
    Optional<SeatEntity> findSeatById(Long SeatId);
    List<SeatEntity> findSeatByScheduleId(Long scheduleId);

}
