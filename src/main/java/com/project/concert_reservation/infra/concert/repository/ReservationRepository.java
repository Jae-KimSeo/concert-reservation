package com.project.concert_reservation.infra.concert.repository;

import com.project.concert_reservation.domain.concert.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Reservation addReservation(Reservation reservation);
    Optional<Reservation> findReservationById(Long reservationId);
    List<Reservation> findReservationByHolderId(Long holderId);
    Reservation updateReservation(Reservation reservation);
}
