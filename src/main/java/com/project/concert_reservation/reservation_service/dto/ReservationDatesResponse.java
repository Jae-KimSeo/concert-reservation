package com.project.concert_reservation.reservation_service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class ReservationDatesResponse {
    private List<String> scheduleIds;
    private List<String> scheduleDates;
}
