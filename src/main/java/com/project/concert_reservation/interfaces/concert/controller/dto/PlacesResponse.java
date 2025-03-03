package com.project.concert_reservation.interfaces.concert.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlacesResponse {
    private List<Long> ids;
    private List<String> names;
    private List<Integer> capacities;
}
