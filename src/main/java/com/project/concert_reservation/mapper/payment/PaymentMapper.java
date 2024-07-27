package com.project.concert_reservation.mapper.payment;

import com.project.concert_reservation.domain.payment.entity.PaymentEntity;
import com.project.concert_reservation.domain.payment.model.Payment;
import com.project.concert_reservation.mapper.concert.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentMapper {
    // Is it okay that mapper reference mapper?
    private final ReservationMapper reservationMapper;

    public PaymentEntity entityToDomain(Payment payment){
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setId(payment.getId());
        paymentEntity.setPaymentStatus(payment.getPaymentStatus());

        return paymentEntity;
    }
}
