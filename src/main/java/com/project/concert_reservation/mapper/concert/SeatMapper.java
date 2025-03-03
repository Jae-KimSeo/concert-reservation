package com.project.concert_reservation.mapper.concert;

import com.project.concert_reservation.domain.concert.entity.SeatEntity;
import com.project.concert_reservation.domain.concert.model.Seat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeatMapper {
    public Seat entityToDomain(SeatEntity seatEntity){
        Seat seat = new Seat();
        seat.setId(seatEntity.getId());
        seat.setScheduleId(seatEntity.getScheduleId());
        seat.setPrice(seatEntity.getPrice());

        return seat;
    }

    public List<Seat> entityToDomainBatch(List<SeatEntity> seatEntities){
        List<Seat> seats = new ArrayList<>();
        for (SeatEntity seatEntity : seatEntities) {
            seats.add(entityToDomain(seatEntity));
        }
        return seats;
    }

    public SeatEntity domainToEntity(Seat seat){
        SeatEntity seatEntity = new SeatEntity();
        seatEntity.setId(seat.getId());
        seatEntity.setScheduleId(seat.getScheduleId());
        seatEntity.setPrice(seat.getPrice());

        return seatEntity;
    }
}
