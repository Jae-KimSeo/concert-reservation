package com.project.concert_reservation.reservation_service.mapper;

import com.project.concert_reservation.reservation_service.Business.domain.PlaceDomain;
import com.project.concert_reservation.reservation_service.Business.domain.ScheduleDomain;
import com.project.concert_reservation.reservation_service.Infrastructure.entity.Place;
import com.project.concert_reservation.reservation_service.Infrastructure.entity.Schedule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleMapper {
    public ScheduleDomain entityToDomain(Schedule schedule){
        ScheduleDomain scheduleDomain = new ScheduleDomain();
        scheduleDomain.setConcertId(schedule.getConcertId());
        scheduleDomain.setPlaceId(schedule.getPlaceId());
        scheduleDomain.setEventDate(schedule.getEventDate());
        scheduleDomain.setReserveOpenAt(schedule.getReserveOpenAt());
        scheduleDomain.setReserveCloseAt(schedule.getReserveCloseAt());

        return scheduleDomain;
    }

    public List<ScheduleDomain> entityToDomainBatch(List<Schedule> schedules){
        List<ScheduleDomain> scheduleDomains = new ArrayList<>();
        for (Schedule schedule : schedules) {
            scheduleDomains.add(entityToDomain(schedule));
        }
        return scheduleDomains;
    }
}
