package com.project.concert_reservation.application.payment.service;

import com.project.concert_reservation.domain.concert.entity.Reservation;
import com.project.concert_reservation.domain.payment.entity.PaymentEntity;
import com.project.concert_reservation.domain.payment.model.Payment;
import com.project.concert_reservation.domain.payment.port.PaymentRepository;
import com.project.concert_reservation.infra.concert.repository.ReservationRepository;
import com.project.concert_reservation.mapper.concert.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public Long makePayment(Long userId, Long reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findReservationById(userId);
        if (optionalReservation.isPresent()) {
            Payment payment = new Payment();
            // TODO : Set reservation ay payment
            payment.setPaymentStatus(Payment.PaymentStatus.Success);
            return paymentRepository.addPayment(payment).getId();
        } else {
            return null;
        }
    }
}
