package com.project.concert_reservation.mapper.concert;

import com.project.concert_reservation.domain.concert.entity.ReservationEntity;
import com.project.concert_reservation.domain.concert.model.Reservation;
import com.project.concert_reservation.interfaces.concert.controller.dto.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationMapper {
    private final SeatMapper seatMapper;

    public Reservation entityToDomain(ReservationEntity reservationEntity){
        Reservation reservation = new Reservation();
        reservation.setHolderId(reservationEntity.getHolderId());
        reservation.setSeatId(reservationEntity.getId());
        reservation.setReservedAt(reservationEntity.getReservedAt());
        reservation.setPaidAt(reservationEntity.getPaidAt());

        return reservation;
    }

    public ReservationEntity domainToEntity(Reservation reservation){
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setSeatId(reservation.getId());
        reservationEntity.setHolderId(reservation.getHolderId());
        reservationEntity.setReservedAt(reservation.getReservedAt());
        reservationEntity.setPaidAt(reservation.getPaidAt());

        return reservationEntity;
    }

    public ReservationResponse  domainToResponse(Reservation reservation){
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setReservedAt(reservation.getReservedAt());
        reservationResponse.setSeatId(reservation.getSeatId());

        return reservationResponse;
    }
}
