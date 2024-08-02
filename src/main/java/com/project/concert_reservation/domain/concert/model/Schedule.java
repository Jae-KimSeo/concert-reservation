package com.project.concert_reservation.domain.concert.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Schedule {
    private Long id;
    private Long concertId;
    private Long placeId;
    private LocalDateTime reserveOpenAt;
    private LocalDateTime reserveCloseAt;
    private LocalDateTime eventDate;
}
