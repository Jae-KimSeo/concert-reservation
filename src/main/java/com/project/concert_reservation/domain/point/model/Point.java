package com.project.concert_reservation.domain.point.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Point {
    private Long id;

    private final Long userId;

    private Long point;

    public Point (Long userId){
        this.userId = userId;
    }

    public Long rechargePoint(Long rechargePoint){
        this.point = this.point + rechargePoint;
        return this.point;
    }

    public Long spendPoint(Long spendPoint){
        this.point = this.point - spendPoint;
        return this.point;
    }

}
