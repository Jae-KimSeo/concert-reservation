package com.project.concert_reservation.mapper.concert;

import com.project.concert_reservation.domain.concert.model.Place;
import com.project.concert_reservation.domain.concert.entity.PlaceEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaceMapper {
    public Place entityToDomain(PlaceEntity placeEntity) {
        Place place = new Place();
        place.setName(placeEntity.getName());
        place.setCapacity(placeEntity.getCapacity());

        return place;
    }

    public List<Place> entityToDomainBatch(List<PlaceEntity> placeEntities){
        List<Place> places = new ArrayList<>();
        for (PlaceEntity placeEntity : placeEntities) {
            places.add(entityToDomain(placeEntity));
        }
        return places;
    }
}
