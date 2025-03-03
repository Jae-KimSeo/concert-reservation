package com.project.concert_reservation.infra.payment.repository;

import com.project.concert_reservation.domain.payment.entity.PaymentEntity;
import com.project.concert_reservation.domain.payment.port.PaymentRepository;
import com.project.concert_reservation.infra.payment.repository.orm.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;

    public PaymentEntity addPayment(PaymentEntity paymentEntity){
        return paymentJpaRepository.save(paymentEntity);
    }
}
