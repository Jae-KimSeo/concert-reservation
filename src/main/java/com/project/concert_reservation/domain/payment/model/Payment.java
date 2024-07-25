package com.project.concert_reservation.domain.payment.model;

import com.project.concert_reservation.domain.concert.domain.ReservationDomain;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Payment {
    private Long id;
    private Long price;
    private ReservationDomain reservationDomain;
    private PaymentStatus paymentStatus;

    public enum PaymentStatus {
        Progress, Success, Fail
    }
}
