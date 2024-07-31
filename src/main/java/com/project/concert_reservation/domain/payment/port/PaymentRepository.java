package com.project.concert_reservation.domain.payment.port;

import com.project.concert_reservation.domain.payment.entity.PaymentEntity;
import com.project.concert_reservation.domain.payment.model.Payment;

public interface PaymentRepository {
    PaymentEntity addPayment(Payment payment);
}
