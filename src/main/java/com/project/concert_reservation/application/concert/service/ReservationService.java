package com.project.concert_reservation.application.concert.service;

import com.project.concert_reservation.domain.concert.domain.ConcertDomain;
import com.project.concert_reservation.domain.concert.domain.PlaceDomain;
import com.project.concert_reservation.domain.concert.domain.ScheduleDomain;
import com.project.concert_reservation.domain.concert.dto.ReservationDTO;
import com.project.concert_reservation.domain.concert.dto.SeatDTO;
import com.project.concert_reservation.domain.concert.entity.Seat;

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
