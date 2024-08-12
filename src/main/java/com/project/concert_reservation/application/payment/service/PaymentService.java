package com.project.concert_reservation.application.payment.service;

import com.project.concert_reservation.domain.concert.entity.ReservationEntity;
import com.project.concert_reservation.domain.payment.model.Payment;
import com.project.concert_reservation.domain.payment.port.PaymentRepository;
import com.project.concert_reservation.domain.concert.port.ReservationRepository;
import com.project.concert_reservation.mapper.payment.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;
    private final PaymentMapper paymentMapper;

    public Payment makePayment(Long userId, Long reservationId) {
        Optional<ReservationEntity> optionalReservation = reservationRepository.findReservationById(userId);
        if (optionalReservation.isPresent()) {
            Payment payment = new Payment();
            payment.setReservationId(reservationId);
            payment.setPaymentStatus(Payment.PaymentStatus.Success);
            payment.setId(paymentRepository.addPayment(paymentMapper.domainToEntity(payment)).getId());
            return payment;
        } else {
            return null;
        }
    }
}
