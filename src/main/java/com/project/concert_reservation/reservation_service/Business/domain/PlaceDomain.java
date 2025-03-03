package com.project.concert_reservation.reservation_service.Business.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceDomain {
    private Long id;
    private String name;
    private int capacity;
}
