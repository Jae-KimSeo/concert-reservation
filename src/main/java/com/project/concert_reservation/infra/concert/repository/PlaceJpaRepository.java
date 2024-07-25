package com.project.concert_reservation.infra.concert.repository;

import com.project.concert_reservation.domain.concert.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceJpaRepository extends JpaRepository<Place, Long> {
    List<Place> findPlaceById(Long concertId);
}
