package com.project.concert_reservation.reservation_service.mapper;

import com.project.concert_reservation.reservation_service.Business.domain.ConcertDomain;
import com.project.concert_reservation.reservation_service.Infrastructure.entity.Concert;
import org.springframework.stereotype.Component;

@Component
public class ConcertMapper {
    public ConcertDomain entityToDomain(Concert concert){
           ConcertDomain concertDomain = new ConcertDomain();
           concertDomain.setName(concert.getName());

           return concertDomain;
    }
}
