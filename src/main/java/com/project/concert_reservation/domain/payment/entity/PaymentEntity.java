package com.project.concert_reservation.domain.payment.entity;

import com.project.concert_reservation.domain.concert.entity.Reservation;
import com.project.concert_reservation.domain.payment.model.Payment;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentEntity {
    private Long id;
    @OneToOne
    private Reservation reservation;
    private LocalDateTime createdAt;
    private Payment.PaymentStatus paymentStatus;
}
