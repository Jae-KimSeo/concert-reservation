package com.project.concert_reservation.infra.concert.repository;

import com.project.concert_reservation.domain.concert.entity.ScheduleEntity;
import com.project.concert_reservation.domain.concert.port.ScheduleRepository;
import com.project.concert_reservation.infra.concert.repository.orm.ScheduleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final ScheduleJpaRepository scheduleJpaRepository;

    public ScheduleEntity addSchedule(ScheduleEntity scheduleEntity){
        return scheduleJpaRepository.save(scheduleEntity);
    }

    public List<ScheduleEntity> findScheduleByConcertAndPlace(Long concertId, Long placeId){
        return scheduleJpaRepository.findScheduleByConcertIdAndPlaceId(concertId, placeId);
    }
}
