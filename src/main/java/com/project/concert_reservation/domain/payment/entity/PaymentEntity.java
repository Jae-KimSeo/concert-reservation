package com.project.concert_reservation.domain.payment.entity;

import com.project.concert_reservation.domain.concert.entity.ReservationEntity;
import com.project.concert_reservation.domain.payment.model.Payment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long reservationId;
    private LocalDateTime createdAt;
    private Payment.PaymentStatus paymentStatus;
}
