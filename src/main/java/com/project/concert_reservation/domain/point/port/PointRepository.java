package com.project.concert_reservation.domain.point.port;

import com.project.concert_reservation.domain.point.entity.PointEntity;

import java.util.Optional;

public interface PointRepository {
    PointEntity addPoint(PointEntity pointEntity);
    PointEntity updatePoint(Long userId, Long point);
    Optional<PointEntity> findPointByUserId(Long userId);
    boolean existsByUserId(Long userId);
    Long findLastUserIdElement();
}
