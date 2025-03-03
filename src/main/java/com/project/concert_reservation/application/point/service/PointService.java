package com.project.concert_reservation.application.point.service;

import com.project.concert_reservation.domain.point.entity.PointEntity;
import com.project.concert_reservation.domain.point.model.Point;
import com.project.concert_reservation.domain.point.port.PointRepository;
import com.project.concert_reservation.mapper.point.PointMapper;
import com.project.concert_reservation.support.exception.CustomException;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        if (point == null) {
            throw new CustomException.PointNotFoundException(userId);
        }

        point.spendPoint(spendPoint);
        return updatePoint(point);
    }

    public List<Point> createPointBatch(Integer num) {
        List<Point> points = new ArrayList<>();

        Long lastUserId = pointRepository.findLastUserIdElement();
        for (int i = 1; i <= num; i++) {
            PointEntity pointEntity = new PointEntity();
            if (lastUserId != null) {
                pointEntity.setUserId(lastUserId + (long)i);
            } else {
                pointEntity.setUserId((long)i);
            }
            pointEntity.setPoint(0L);

            points.add(pointMapper.entityToDomain(pointRepository.addPoint(pointEntity)));
        }

        return points;
    }

    @Nullable
    private Point updatePoint(Point point) {
        return pointMapper.entityToDomain(pointRepository.updatePoint(point.getUserId(), point.getPoint()));
    }
}
