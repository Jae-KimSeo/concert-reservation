package com.project.concert_reservation.infra.concert.repository;

import com.project.concert_reservation.domain.concert.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaRepository reservationJpaRepository;

    public Reservation addReservation(Reservation reservation){
        reservation.setReservedAt(LocalDateTime.now());
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setUpdatedAt(LocalDateTime.now());

        return reservationJpaRepository.save(reservation);
    }

    public Optional<Reservation> findReservationById(Long id){
        return reservationJpaRepository.findById(id);
    }
    public List<Reservation> findReservationByHolderId(Long holderId){
        return reservationJpaRepository.findReservationByHolderId(holderId);
    }

    public Reservation updateReservation(Reservation reservation){
        reservation.setUpdatedAt(LocalDateTime.now());

        return reservationJpaRepository.save(reservation);
    }
}
