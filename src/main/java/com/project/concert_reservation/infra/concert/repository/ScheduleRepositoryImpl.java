package com.project.concert_reservation.infra.concert.repository;

import com.project.concert_reservation.domain.concert.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final ScheduleJpaRepository scheduleJpaRepository;

    public Schedule addSchedule(Schedule schedule){
        return scheduleJpaRepository.save(schedule);
    }

    public List<Schedule> findScheduleByConcertAndPlace(Long concertId, Long placeId){
        return scheduleJpaRepository.findScheduleByConcertIdAndPlaceId(concertId, placeId);
    }
}
