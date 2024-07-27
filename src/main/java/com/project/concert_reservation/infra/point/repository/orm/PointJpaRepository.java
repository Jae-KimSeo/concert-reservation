package com.project.concert_reservation.infra.point.repository.orm;

import com.project.concert_reservation.domain.point.entity.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointJpaRepository extends JpaRepository<PointEntity, Long> {
    Optional<PointEntity> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
