package com.project.concert_reservation.reservation_service.Infrastructure.Repository;

import com.project.concert_reservation.reservation_service.Infrastructure.entity.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository {
    Place addPlace(Place place);
    List<Place> findAll();
    List<Place> findPlaceById(Long Id);
}
