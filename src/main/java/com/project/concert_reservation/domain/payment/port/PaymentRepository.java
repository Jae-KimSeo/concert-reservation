package com.project.concert_reservation.domain.payment.port;

import com.project.concert_reservation.domain.payment.entity.PaymentEntity;

public interface PaymentRepository {
    PaymentEntity addPayment(PaymentEntity paymentEntity);
}
