package com.project.concert_reservation.infra.concert.repository;

import com.project.concert_reservation.domain.concert.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    Schedule addSchedule(Schedule schedule);
    List<Schedule> findScheduleByConcertAndPlace(Long concertId, Long placeId);
}
