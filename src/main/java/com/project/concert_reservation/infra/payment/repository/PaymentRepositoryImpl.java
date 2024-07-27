package com.project.concert_reservation.infra.payment.repository;

import com.project.concert_reservation.domain.payment.entity.PaymentEntity;
import com.project.concert_reservation.domain.payment.model.Payment;
import com.project.concert_reservation.infra.payment.repository.orm.PaymentJpaRepository;
import com.project.concert_reservation.mapper.payment.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PaymentRepositoryImpl {
    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentMapper paymentMapper;

    public PaymentEntity addPayment(Payment payment){
        return paymentJpaRepository.save(paymentMapper.entityToDomain(payment));
    }
}
