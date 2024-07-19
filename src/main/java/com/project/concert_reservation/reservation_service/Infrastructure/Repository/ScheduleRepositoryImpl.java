package com.project.concert_reservation.reservation_service.Infrastructure.Repository;

import com.project.concert_reservation.reservation_service.Infrastructure.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
