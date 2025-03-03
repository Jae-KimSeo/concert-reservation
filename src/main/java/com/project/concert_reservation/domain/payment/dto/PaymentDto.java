package com.project.concert_reservation.domain.payment.dto;

import com.project.concert_reservation.domain.payment.model.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {
    private Long id;
    private Long leftPoint;
    private Payment.PaymentStatus paymentStatus;
}
