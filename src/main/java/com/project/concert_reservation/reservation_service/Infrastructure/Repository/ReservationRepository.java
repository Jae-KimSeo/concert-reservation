package com.project.concert_reservation.reservation_service.Infrastructure.Repository;

import com.project.concert_reservation.reservation_service.Infrastructure.entity.Reservation;

import java.util.List;

public interface ReservationRepository {
    Reservation addReservation(Reservation reservation);
    List<Reservation> findReservationByHolderId(String holderId);
}
