package com.project.concert_reservation.mapper.concert;

import com.project.concert_reservation.domain.concert.dto.SeatDTO;
import com.project.concert_reservation.domain.concert.entity.SeatEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeatMapper {
    public SeatDTO entityToDTO(SeatEntity seatEntity){
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setPrice(seatEntity.getPrice());

        return seatDTO;
    }

    public List<SeatDTO> entityToDTOBatch(List<SeatEntity> seatEntities){
        List<SeatDTO> seatDTOs = new ArrayList<>();
        for (SeatEntity seatEntity : seatEntities) {
            seatDTOs.add(entityToDTO(seatEntity));
        }
        return seatDTOs;
    }

    public SeatEntity dtoToEntity(SeatDTO seatDTO){
        SeatEntity seatEntity = new SeatEntity();
        seatEntity.setPrice(seatDTO.getPrice());

        return seatEntity;
    }
}
