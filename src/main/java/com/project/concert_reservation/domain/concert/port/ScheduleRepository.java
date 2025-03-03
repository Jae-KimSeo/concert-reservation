package com.project.concert_reservation.domain.concert.port;

import com.project.concert_reservation.domain.concert.entity.ScheduleEntity;

import java.util.List;

public interface ScheduleRepository {
    ScheduleEntity addSchedule(ScheduleEntity scheduleEntity);
    List<ScheduleEntity> findScheduleByConcertAndPlace(Long concertId, Long placeId);
}
