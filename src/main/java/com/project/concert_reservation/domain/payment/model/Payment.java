package com.project.concert_reservation.domain.payment.model;

import com.project.concert_reservation.domain.concert.domain.ReservationDomain;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Payment {
    private Long id;
    private ReservationDomain reservationDomain;
    private PaymentStatus paymentStatus;

    public enum PaymentStatus {
        Progress, Success, Fail
    }
}
