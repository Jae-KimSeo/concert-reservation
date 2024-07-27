package com.project.concert_reservation.interfaces.payment.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PaymentRequest {
    private Long reservationId;
    private Long seatId;
    private Long seatPrice;
}
