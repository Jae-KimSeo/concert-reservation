package com.project.concert_reservation.infra.concert.repository;

import com.project.concert_reservation.domain.concert.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findReservationByHolderId(String holderId);
}
