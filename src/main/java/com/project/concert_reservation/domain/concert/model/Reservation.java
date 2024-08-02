package com.project.concert_reservation.domain.concert.model;

import com.project.concert_reservation.domain.concert.entity.ReservationEntity;
import com.project.concert_reservation.domain.concert.entity.SeatEntity;
import com.project.concert_reservation.domain.concert.port.ReservationRepository;
import com.project.concert_reservation.domain.concert.port.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Reservation {
    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;

    public SeatEntity getReservedSeatInfo(Long userId){
        return reservationRepository.findReservationByHolderId(userId).getFirst().getSeatEntity();
    }

    public void setPaidAt(Long reservationId, LocalDateTime paidAt){
        Optional<ReservationEntity> reservation = reservationRepository.findReservationById(reservationId);
        if (reservation.isPresent()) {
            reservation.get().setPaidAt(paidAt);
        } else {
            // TODO : Add Error code?
            return;
        }
    }
}
