package com.project.concert_reservation.mapper.concert;

import com.project.concert_reservation.domain.concert.domain.PlaceDomain;
import com.project.concert_reservation.domain.concert.entity.Place;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaceMapper {
    public PlaceDomain entityToDomain(Place place) {
        PlaceDomain placeDomain = new PlaceDomain();
        placeDomain.setName(place.getName());
        placeDomain.setCapacity(place.getCapacity());

        return placeDomain;
    }

    public List<PlaceDomain> entityToDomainBatch(List<Place> places){
        List<PlaceDomain> placeDomains = new ArrayList<>();
        for (Place place : places) {
            placeDomains.add(entityToDomain(place));
        }
        return placeDomains;
    }
}
