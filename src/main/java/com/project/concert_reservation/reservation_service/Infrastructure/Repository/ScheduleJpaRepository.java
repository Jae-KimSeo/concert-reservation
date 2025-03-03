package com.project.concert_reservation.reservation_service.Infrastructure.Repository;

import com.project.concert_reservation.reservation_service.Infrastructure.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleJpaRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findScheduleByConcertIdAndPlaceId(Long concertId, Long placeId);
}
