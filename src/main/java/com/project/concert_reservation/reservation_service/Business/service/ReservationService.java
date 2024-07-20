package com.project.concert_reservation.reservation_service.Business.service;

import com.project.concert_reservation.reservation_service.Business.domain.*;

import java.util.List;

public interface ReservationService {
    ConcertDomain getConcertInfo(Long concertId);
    List<PlaceDomain> getPlaceInfo(Long concertId);
    List<ScheduleDomain> getScheduleInfo(Long concertId, Long placeId);
    List<SeatDomain> getSeatsInfo(Long scheduleId);
    ReservationDomain makeReservation(Long seatId);
}
