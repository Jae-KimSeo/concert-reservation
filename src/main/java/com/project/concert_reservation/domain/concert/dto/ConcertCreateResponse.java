package com.project.concert_reservation.domain.concert.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConcertCreateResponse {
    private List<Long> ids;
    private List<String> names;
}
