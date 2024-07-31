package com.project.concert_reservation.interfaces.payment.controller.dto;

import com.project.concert_reservation.domain.concert.domain.ReservationDomain;
import com.project.concert_reservation.domain.payment.model.Payment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PaymentResponse {
    private Long billId;
    // TODO : Return Seat and reservation info if it needed
    //private String seatId;
    //private ReservationDomain reservation;
    private Payment.PaymentStatus paymentStatus;
}