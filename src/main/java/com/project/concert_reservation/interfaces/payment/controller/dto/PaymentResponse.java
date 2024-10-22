package com.project.concert_reservation.interfaces.payment.controller.dto;

import com.project.concert_reservation.domain.payment.model.Payment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PaymentResponse {
    private Long id;
    private Long leftPoint;
    private Payment.PaymentStatus paymentStatus;
}