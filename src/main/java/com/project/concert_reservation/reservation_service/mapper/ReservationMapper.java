package com.project.concert_reservation.reservation_service.mapper;

import com.project.concert_reservation.reservation_service.Business.dto.ReservationDTO;
import com.project.concert_reservation.reservation_service.Infrastructure.entity.Reservation;
import com.project.concert_reservation.reservation_service.Interfaces.controller.dto.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationMapper {
    private final SeatMapper seatMapper;

    public ReservationDTO entityToDTO(Reservation reservation){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setHolderId(reservation.getHolderId());
        reservationDTO.setSeatDTO(seatMapper.entityToDTO(reservation.getSeat()));
        reservationDTO.setReservedAt(reservation.getReservedAt());
        reservationDTO.setPaidAt(reservation.getPaidAt());

        return reservationDTO;
    }

    public Reservation DTOToEntity(ReservationDTO reservationDTO){
        Reservation reservation = new Reservation();
        reservation.setSeat(seatMapper.dtoToEntity(reservationDTO.getSeatDTO()));
        reservation.setHolderId(reservationDTO.getHolderId());
        reservation.setReservedAt(reservationDTO.getReservedAt());
        reservation.setPaidAt(reservationDTO.getPaidAt());

        return reservation;
    }

    public ReservationResponse  domainToResponse(ReservationDTO reservationDTO){
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setReservedAt(reservationDTO.getReservedAt());
        reservationResponse.setSeatId(reservationDTO.getSeatDTO().getId());
        reservationResponse.setSeatPrice(reservationDTO.getSeatDTO().getPrice());

        return reservationResponse;
    }
}
