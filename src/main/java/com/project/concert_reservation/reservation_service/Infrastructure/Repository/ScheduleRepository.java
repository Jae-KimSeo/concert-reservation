package com.project.concert_reservation.reservation_service.Infrastructure.Repository;

import com.project.concert_reservation.reservation_service.Infrastructure.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    Schedule addSchedule(Schedule schedule);
    List<Schedule> findScheduleByConcertAndPlace(Long concertId, Long placeId);
}
