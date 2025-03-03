package com.project.concert_reservation.domain.payment.model;

import com.project.concert_reservation.domain.concert.model.Reservation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Payment {
    private Long id;
    private Long reservationId;
    private PaymentStatus paymentStatus;

    public enum PaymentStatus {
        Progress, Success, Fail
    }
}
