package com.project.concert_reservation.infra.concert.repository.orm;

import com.project.concert_reservation.domain.concert.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatJpaRepository extends JpaRepository<SeatEntity, Long> {
    List<SeatEntity> findSeatByScheduleId(Long ScheduleId);
}
