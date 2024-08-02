package com.project.concert_reservation.mapper.concert;

import com.project.concert_reservation.domain.concert.model.Schedule;
import com.project.concert_reservation.domain.concert.entity.ScheduleEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleMapper {
    public Schedule entityToDomain(ScheduleEntity scheduleEntity){
        Schedule schedule = new Schedule();
        schedule.setConcertId(scheduleEntity.getConcertId());
        schedule.setPlaceId(scheduleEntity.getPlaceId());
        schedule.setEventDate(scheduleEntity.getEventDate());
        schedule.setReserveOpenAt(scheduleEntity.getReserveOpenAt());
        schedule.setReserveCloseAt(scheduleEntity.getReserveCloseAt());

        return schedule;
    }

    public List<Schedule> entityToDomainBatch(List<ScheduleEntity> scheduleEntities){
        List<Schedule> schedules = new ArrayList<>();
        for (ScheduleEntity scheduleEntity : scheduleEntities) {
            schedules.add(entityToDomain(scheduleEntity));
        }
        return schedules;
    }
}
