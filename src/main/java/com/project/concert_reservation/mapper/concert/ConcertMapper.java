package com.project.concert_reservation.mapper.concert;

import com.project.concert_reservation.domain.concert.entity.ConcertEntity;
import com.project.concert_reservation.domain.concert.model.Concert;
import org.springframework.stereotype.Component;

@Component
public class ConcertMapper {
    public Concert entityToDomain(ConcertEntity concertEntity){
           Concert concert = new Concert();
           concert.setId(concertEntity.getId());
           concert.setName(concertEntity.getName());

           return concert;
    }
}
