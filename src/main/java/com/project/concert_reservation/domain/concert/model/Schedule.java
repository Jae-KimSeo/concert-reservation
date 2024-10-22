package com.project.concert_reservation.domain.concert.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@Component
public class Schedule {
    private Long id;
    private Long concertId;
    private Long placeId;
    private LocalDateTime reserveOpenAt;
    private LocalDateTime reserveCloseAt;
    private LocalDateTime eventDate;
}
