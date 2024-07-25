package com.project.concert_reservation.mapper.concert;

import com.project.concert_reservation.domain.concert.domain.ConcertDomain;
import com.project.concert_reservation.domain.concert.entity.Concert;
import org.springframework.stereotype.Component;

@Component
public class ConcertMapper {
    public ConcertDomain entityToDomain(Concert concert){
           ConcertDomain concertDomain = new ConcertDomain();
           concertDomain.setName(concert.getName());

           return concertDomain;
    }
}
