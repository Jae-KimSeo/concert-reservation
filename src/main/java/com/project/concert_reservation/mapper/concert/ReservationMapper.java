package com.project.concert_reservation.mapper.concert;

import com.project.concert_reservation.domain.concert.dto.ReservationDTO;
import com.project.concert_reservation.domain.concert.entity.ReservationEntity;
import com.project.concert_reservation.interfaces.concert.controller.dto.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationMapper {
    private final SeatMapper seatMapper;

    public ReservationDTO entityToDTO(ReservationEntity reservationEntity){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setHolderId(reservationEntity.getHolderId());
        reservationDTO.setSeatDTO(seatMapper.entityToDTO(reservationEntity.getSeatEntity()));
        reservationDTO.setReservedAt(reservationEntity.getReservedAt());
        reservationDTO.setPaidAt(reservationEntity.getPaidAt());

        return reservationDTO;
    }

    public ReservationEntity DTOToEntity(ReservationDTO reservationDTO){
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setSeatEntity(seatMapper.dtoToEntity(reservationDTO.getSeatDTO()));
        reservationEntity.setHolderId(reservationDTO.getHolderId());
        reservationEntity.setReservedAt(reservationDTO.getReservedAt());
        reservationEntity.setPaidAt(reservationDTO.getPaidAt());

        return reservationEntity;
    }

    public ReservationResponse  domainToResponse(ReservationDTO reservationDTO){
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setReservedAt(reservationDTO.getReservedAt());
        reservationResponse.setSeatId(reservationDTO.getSeatDTO().getId());
        reservationResponse.setSeatPrice(reservationDTO.getSeatDTO().getPrice());

        return reservationResponse;
    }
}
