package com.project.concert_reservation.domain.concert.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceDomain {
    private Long id;
    private String name;
    private int capacity;
}
