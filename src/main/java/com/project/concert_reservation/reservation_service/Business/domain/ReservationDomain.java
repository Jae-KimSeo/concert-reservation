package com.project.concert_reservation.reservation_service.Business.domain;

import com.project.concert_reservation.reservation_service.Infrastructure.Repository.ReservationRepository;
import com.project.concert_reservation.reservation_service.Infrastructure.Repository.SeatRepository;
import com.project.concert_reservation.reservation_service.Infrastructure.entity.Reservation;
import com.project.concert_reservation.reservation_service.Infrastructure.entity.Seat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
public class ReservationDomain {
    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;

    public Seat getReservedSeatInfo(String userId){
        return reservationRepository.findReservationByHolderId(userId).getFirst().getSeat();
    }

    public void setPaidAt(Long reservationId, LocalDateTime paidAt){
        Optional<Reservation> reservation = reservationRepository.findReservationById(reservationId);
        if (reservation.isPresent()) {
            reservation.get().setPaidAt(paidAt);
        } else {
            // TODO : Add Error code?
            return;
        }
    }
}
