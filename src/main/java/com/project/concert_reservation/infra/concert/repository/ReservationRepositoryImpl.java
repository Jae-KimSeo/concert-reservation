package com.project.concert_reservation.infra.concert.repository;

import com.project.concert_reservation.domain.concert.entity.ReservationEntity;
import com.project.concert_reservation.domain.concert.port.ReservationRepository;
import com.project.concert_reservation.infra.concert.repository.orm.ReservationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaRepository reservationJpaRepository;

    public ReservationEntity addReservation(ReservationEntity reservationEntity){
        reservationEntity.setReservedAt(LocalDateTime.now());
        reservationEntity.setCreatedAt(LocalDateTime.now());
        reservationEntity.setUpdatedAt(LocalDateTime.now());

        return reservationJpaRepository.save(reservationEntity);
    }

    public Optional<ReservationEntity> findReservationById(Long id){
        return reservationJpaRepository.findById(id);
    }
    public List<ReservationEntity> findReservationByHolderId(Long holderId){
        return reservationJpaRepository.findReservationByHolderId(holderId);
    }

    public ReservationEntity updateReservation(ReservationEntity reservationEntity){
        reservationEntity.setUpdatedAt(LocalDateTime.now());

        return reservationJpaRepository.save(reservationEntity);
    }
}
