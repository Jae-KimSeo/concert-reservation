package com.project.concert_reservation.domain.concert.port;

import com.project.concert_reservation.domain.concert.entity.PlaceEntity;

import java.util.List;

public interface PlaceRepository {
    PlaceEntity addPlace(PlaceEntity placeEntity);
    List<PlaceEntity> findAll();
    List<PlaceEntity> findPlaceById(Long Id);
}
