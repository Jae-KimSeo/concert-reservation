package com.project.concert_reservation.reservation_service.Infrastructure.Repository;

import com.project.concert_reservation.reservation_service.Infrastructure.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Reservation addReservation(Reservation reservation);
    Optional<Reservation> findReservationById(Long reservationId);
    List<Reservation> findReservationByHolderId(String holderId);
    Reservation updateReservation(Reservation reservation);
}
