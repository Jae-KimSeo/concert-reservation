package com.project.concert_reservation.reservation_service.Business.service;

import com.project.concert_reservation.reservation_service.Business.domain.*;
import com.project.concert_reservation.reservation_service.Business.dto.ReservationDTO;
import com.project.concert_reservation.reservation_service.Business.dto.SeatDTO;
import com.project.concert_reservation.reservation_service.Infrastructure.entity.Seat;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    ConcertDomain getConcertInfo(Long concertId);
    List<PlaceDomain> getPlaceInfo(Long concertId);
    List<ScheduleDomain> getScheduleInfo(Long concertId, Long placeId);
    List<SeatDTO> getSeatsInfo(Long scheduleId);
    ReservationDTO makeReservation(Long seatId);
    Seat getReservedSeatInfo(String userId);
    void setPaidAt(Long reservationId);
}
