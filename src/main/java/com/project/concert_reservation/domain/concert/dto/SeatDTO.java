package com.project.concert_reservation.domain.concert.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SeatDTO {
    private Long id;

    private Long scheduleId;
    private Long price;
}
