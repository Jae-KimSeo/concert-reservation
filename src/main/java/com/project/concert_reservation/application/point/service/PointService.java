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

    public Point rechargePoint(Long userId, Long point){
        return updatePoint(userId, point);
    }

    public Point spendPoint(Long userId, Long point){
        return updatePoint(userId, (-1) * point);
    }

    @Nullable
    private Point updatePoint(Long userId, Long point) {
        Optional<PointEntity> optionalEntity = pointRepository.findPointByUserId(userId);
        if(optionalEntity.isPresent()){
            PointEntity pointEntity = optionalEntity.get();
            pointEntity.setPoint(optionalEntity.get().getPoint() + point);
            return pointMapper.entityToDomain(pointRepository.updatePoint(userId, pointEntity.getPoint()));
        } else {
            // TODO : return error code
            return null;
        }
    }
}
