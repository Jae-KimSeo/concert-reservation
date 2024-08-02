package com.project.concert_reservation.application.point.service;

import com.project.concert_reservation.domain.point.entity.PointEntity;
import com.project.concert_reservation.domain.point.model.Point;
import com.project.concert_reservation.domain.point.port.PointRepository;
import com.project.concert_reservation.mapper.point.PointMapper;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PointService {
    private final PointRepository pointRepository;
    private final PointMapper pointMapper;

    public Point getPoint(Long userId){
        Optional<PointEntity> optionalEntity = pointRepository.findPointByUserId(userId);
        return optionalEntity.map(pointMapper::entityToDomain).orElse(null);
    }

    public Point rechargePoint(Long userId, Long rechargePoint){
        Point point = getPoint(userId);

        point.rechargePoint(rechargePoint);
        return updatePoint(point);
    }

    public Point spendPoint(Long userId, Long spendPoint){
        Point point = getPoint(userId);

        point.spendPoint(spendPoint);
        return updatePoint(point);
    }

    @Nullable
    private Point updatePoint(Point point) {
        return pointMapper.entityToDomain(pointRepository.updatePoint(point.getUserId(), point.getPoint()));
    }
}
