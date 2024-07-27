package com.project.concert_reservation.infra.concert.repository;

import com.project.concert_reservation.domain.concert.entity.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PlaceRepositoryImpl implements PlaceRepository{

    private final PlaceJpaRepository placeJpaRepository;

    public Place addPlace(Place place){
        return placeJpaRepository.save(place);
    }
    public List<Place> findAll(){
        return placeJpaRepository.findAll();
    }
    public List<Place> findPlaceById(Long id){
        return placeJpaRepository.findPlaceById(id);
    }
}
