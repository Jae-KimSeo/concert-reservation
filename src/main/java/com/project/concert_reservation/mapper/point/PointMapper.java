package com.project.concert_reservation.mapper.point;

import com.project.concert_reservation.domain.point.entity.PointEntity;
import com.project.concert_reservation.domain.point.model.Point;
import org.springframework.stereotype.Component;

@Component
public class PointMapper {
    public Point entityToDomain(PointEntity pointEntity){
        return new Point(pointEntity.getUserId());
    }
}
