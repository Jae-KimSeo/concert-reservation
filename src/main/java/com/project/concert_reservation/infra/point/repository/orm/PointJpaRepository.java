package com.project.concert_reservation.infra.point.repository.orm;

import com.project.concert_reservation.domain.point.entity.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PointJpaRepository extends JpaRepository<PointEntity, Long> {
    Optional<PointEntity> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
    @Query(value = "SELECT m.user_id FROM point_entity m ORDER BY m.id DESC LIMIT 1", nativeQuery = true)
    Optional<Long> findLastUserIdElement();
}
