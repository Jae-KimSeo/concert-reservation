package com.project.concert_reservation.interfaces.point.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PointCreateResponse {
    private List<Long> ids;
    private List<Long> userIds;
}
