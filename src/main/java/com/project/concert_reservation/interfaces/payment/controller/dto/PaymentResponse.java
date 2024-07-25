package com.project.concert_reservation.interfaces.payment.controller.dto;

import com.project.concert_reservation.domain.payment.model.Payment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PaymentResponse {
    private String billId;
    private Long price;
    private String buyerId;
    private String scheduleId;
    private String seatId;
    private Payment.PaymentStatus paymentStatus;
}