package com.project.concert_reservation.infra.concert.repository;

import com.project.concert_reservation.domain.concert.entity.PlaceEntity;
import com.project.concert_reservation.domain.concert.port.PlaceRepository;
import com.project.concert_reservation.infra.concert.repository.orm.PlaceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PlaceRepositoryImpl implements PlaceRepository {

    private final PlaceJpaRepository placeJpaRepository;

    public PlaceEntity addPlace(PlaceEntity placeEntity){
        return placeJpaRepository.save(placeEntity);
    }
    public List<PlaceEntity> findAll(){
        return placeJpaRepository.findAll();
    }
    public List<PlaceEntity> findPlaceById(Long id){
        return placeJpaRepository.findPlaceById(id);
    }
}
