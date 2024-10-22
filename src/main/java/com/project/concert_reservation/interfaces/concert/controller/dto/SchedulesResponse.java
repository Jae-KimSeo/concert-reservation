package com.project.concert_reservation.interfaces.concert.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SchedulesResponse {
    private List<Long> scheduleIds;
    private List<LocalDateTime> scheduleDates;
}
