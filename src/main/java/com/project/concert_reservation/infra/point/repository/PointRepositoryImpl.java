package com.project.concert_reservation.infra.point.repository;

import com.project.concert_reservation.domain.point.entity.PointEntity;
import com.project.concert_reservation.domain.point.port.PointRepository;
import com.project.concert_reservation.infra.point.repository.orm.PointJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PointRepositoryImpl implements PointRepository {
    private final PointJpaRepository pointJpaRepository;

    public PointEntity addPoint(Long userId, Long point){
        Optional<PointEntity> optionalPointEntity = findPointByUserId(userId);

        if (optionalPointEntity.isEmpty()) {
            PointEntity newPointEntity = new PointEntity();
            newPointEntity.setUserId(userId);
            newPointEntity.setPoint(point);

            return pointJpaRepository.save(newPointEntity);
        }
        // TODO : Add error log when PointEntity is exists case with given userID

        return null;
    }

    public PointEntity updatePoint(Long userId, Long updatedPointEntity){
        Optional<PointEntity> PointEntity = findPointByUserId(userId);

        if (PointEntity.isPresent()) {
            PointEntity newPointEntity = new PointEntity();
            newPointEntity.setUserId(userId);
            newPointEntity.setPoint(updatedPointEntity);

            return pointJpaRepository.save(newPointEntity);
        }
        // TODO : Add error log when PointEntity is not exists with given userID

        return null;
    }

    public Optional<PointEntity> findPointByUserId(Long userId){
        return pointJpaRepository.findByUserId(userId);
    }

    public boolean existsByUserId(Long userId){
        return pointJpaRepository.existsByUserId(userId);
    }
}
