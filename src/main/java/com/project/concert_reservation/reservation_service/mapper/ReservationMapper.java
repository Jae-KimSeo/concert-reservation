package com.project.concert_reservation.reservation_service.mapper;

import com.project.concert_reservation.reservation_service.Business.domain.ReservationDomain;
import com.project.concert_reservation.reservation_service.Infrastructure.entity.Reservation;
import com.project.concert_reservation.reservation_service.Interfaces.controller.dto.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationMapper {
    private final SeatMapper seatMapper;

    public ReservationDomain entityToDomain(Reservation reservation){
        ReservationDomain reservationDomain = new ReservationDomain();
        reservationDomain.setHolderId(reservation.getHolderId());
        reservationDomain.setSeatDomain(seatMapper.entityToDomain(reservation.getSeat()));
        reservationDomain.setReservedAt(reservation.getReservedAt());
        reservationDomain.setPaidAt(reservation.getPaidAt());

        return reservationDomain;
    }

    public Reservation domainToEntity(ReservationDomain reservationDomain){
        Reservation reservation = new Reservation();
        reservation.setSeat(seatMapper.domainToEntity(reservationDomain.getSeatDomain()));
        reservation.setHolderId(reservationDomain.getHolderId());
        reservation.setReservedAt(reservationDomain.getReservedAt());
        reservation.setPaidAt(reservationDomain.getPaidAt());

        return reservation;
    }

    public ReservationResponse  domainToResponse(ReservationDomain reservationDomain){
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setReservedAt(reservationDomain.getReservedAt());
        reservationResponse.setSeatId(reservationDomain.getSeatDomain().getId());
        reservationResponse.setSeatPrice(reservationDomain.getSeatDomain().getSeatPrice());

        return reservationResponse;
    }
}
