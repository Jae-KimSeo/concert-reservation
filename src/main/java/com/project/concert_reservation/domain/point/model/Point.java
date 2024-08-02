package com.project.concert_reservation.domain.point.model;

import com.project.concert_reservation.domain.point.entity.PointEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Point {
    private Long id;

    @Getter
    private final Long userId;

    @Getter
    private Long point;

    public Long rechargePoint(Long rechargePoint){
        this.point = this.point + rechargePoint;
        return this.point;
    }

    public Long spendPoint(Long spendPoint){
        this.point = this.point - spendPoint;
        return this.point;
    }

}
