package com.project.concert_reservation.domain.point.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class Point {
    private Long id;

    @Setter
    private Long userId;

    @Getter
    private Long point;

    public Long rechargePoint(Long rechargePoint){
        this.point = point + rechargePoint;
        return this.point;
    }

    public Long spendPoint(Long spendPoint){
        this.point = point - spendPoint;
        return this.point;
    }
}
