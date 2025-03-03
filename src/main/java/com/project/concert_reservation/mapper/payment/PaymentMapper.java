package com.project.concert_reservation.mapper.payment;

import com.project.concert_reservation.domain.payment.entity.PaymentEntity;
import com.project.concert_reservation.domain.payment.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentMapper {
    public PaymentEntity domainToEntity(Payment payment){
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setId(payment.getId());
        paymentEntity.setReservationId(payment.getReservationId());
        paymentEntity.setPaymentStatus(payment.getPaymentStatus());

        return paymentEntity;
    }
}
