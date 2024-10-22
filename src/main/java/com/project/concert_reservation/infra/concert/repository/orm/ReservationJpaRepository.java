package com.project.concert_reservation.infra.concert.repository.orm;

import com.project.concert_reservation.domain.concert.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> findReservationByHolderId(Long holderId);
}
