package com.project.concert_reservation.infra.concert.repository.orm;

import com.project.concert_reservation.domain.concert.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleJpaRepository extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findScheduleByConcertIdAndPlaceId(Long concertId, Long placeId);
}
