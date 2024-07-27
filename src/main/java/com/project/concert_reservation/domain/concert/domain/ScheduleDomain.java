package com.project.concert_reservation.domain.concert.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleDomain {
    private Long id;
    private Long concertId;
    private Long placeId;
    private LocalDateTime reserveOpenAt;
    private LocalDateTime reserveCloseAt;
    private LocalDateTime eventDate;
}
