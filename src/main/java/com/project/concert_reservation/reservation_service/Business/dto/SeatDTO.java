package com.project.concert_reservation.reservation_service.Business.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SeatDTO {
    private Long id;

    private Long scheduleId;
    private Long price;
}
