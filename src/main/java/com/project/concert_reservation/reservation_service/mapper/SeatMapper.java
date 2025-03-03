package com.project.concert_reservation.reservation_service.mapper;

import com.project.concert_reservation.reservation_service.Business.domain.SeatDomain;
import com.project.concert_reservation.reservation_service.Infrastructure.entity.Seat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeatMapper {
    public SeatDomain entityToDomain(Seat seat){
        SeatDomain seatDomain = new SeatDomain();
        seatDomain.setSeatPrice(seat.getSeatPrice());

        return seatDomain;
    }

    public List<SeatDomain> entityToDomainBatch(List<Seat> seats){
        List<SeatDomain> seatDomains = new ArrayList<>();
        for (Seat seat : seats) {
            seatDomains.add(entityToDomain(seat));
        }
        return seatDomains;
    }

    public Seat domainToEntity(SeatDomain seatDomain){
        Seat seat = new Seat();
        seat.setSeatPrice(seatDomain.getSeatPrice());

        return seat;
    }
}
