package com.project.concert_reservation.infra.payment.repository.orm;

import com.project.concert_reservation.domain.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {
}
