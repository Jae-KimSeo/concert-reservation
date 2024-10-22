package com.project.concert_reservation.domain.concert.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class Seat {
    private Long id;
    private Long scheduleId;
    private Long price;
}
