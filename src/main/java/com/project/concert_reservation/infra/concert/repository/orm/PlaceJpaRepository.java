package com.project.concert_reservation.infra.concert.repository.orm;

import com.project.concert_reservation.domain.concert.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceJpaRepository extends JpaRepository<PlaceEntity, Long> {
    List<PlaceEntity> findPlaceById(Long concertId);
}
