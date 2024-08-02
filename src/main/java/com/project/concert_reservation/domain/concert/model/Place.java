package com.project.concert_reservation.domain.concert.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Place {
    private Long id;
    private String name;
    private int capacity;
}
