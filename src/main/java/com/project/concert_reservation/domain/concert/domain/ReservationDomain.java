package com.project.concert_reservation.domain.concert.domain;

import com.project.concert_reservation.infra.concert.repository.ReservationRepository;
import com.project.concert_reservation.infra.concert.repository.SeatRepository;
import com.project.concert_reservation.domain.concert.entity.Reservation;
import com.project.concert_reservation.domain.concert.entity.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReservationDomain {
    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;

    public Seat getReservedSeatInfo(Long userId){
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
