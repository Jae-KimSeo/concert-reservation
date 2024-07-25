package com.project.concert_reservation.infra.concert.repository;

import com.project.concert_reservation.domain.concert.entity.Place;

import java.util.List;

public interface PlaceRepository {
    Place addPlace(Place place);
    List<Place> findAll();
    List<Place> findPlaceById(Long Id);
}
