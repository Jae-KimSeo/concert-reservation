package com.project.concert_reservation.domain.concert.port;

import com.project.concert_reservation.domain.concert.entity.ReservationEntity;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    ReservationEntity addReservation(ReservationEntity reservationEntity);
    Optional<ReservationEntity> findReservationById(Long reservationId);
    List<ReservationEntity> findReservationByHolderId(Long holderId);
    ReservationEntity updateReservation(ReservationEntity reservationEntity);
}
