package com.project.concert_reservation.reservation_service.mapper;

import com.project.concert_reservation.reservation_service.Business.domain.SeatDomain;
import com.project.concert_reservation.reservation_service.Business.dto.SeatDTO;
import com.project.concert_reservation.reservation_service.Infrastructure.entity.Seat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeatMapper {
    public SeatDTO entityToDTO(Seat seat){
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setPrice(seat.getPrice());

        return seatDTO;
    }

    public List<SeatDTO> entityToDTOBatch(List<Seat> seats){
        List<SeatDTO> seatDTOs = new ArrayList<>();
        for (Seat seat : seats) {
            seatDTOs.add(entityToDTO(seat));
        }
        return seatDTOs;
    }

    public Seat dtoToEntity(SeatDTO seatDTO){
        Seat seat = new Seat();
        seat.setPrice(seatDTO.getPrice());

        return seat;
    }
}
