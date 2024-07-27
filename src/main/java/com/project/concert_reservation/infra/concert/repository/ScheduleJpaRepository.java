package com.project.concert_reservation.infra.concert.repository;

import com.project.concert_reservation.domain.concert.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleJpaRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findScheduleByConcertIdAndPlaceId(Long concertId, Long placeId);
}
