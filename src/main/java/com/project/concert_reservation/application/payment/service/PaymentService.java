package com.project.concert_reservation.application.payment.service;

import com.project.concert_reservation.domain.payment.port.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public void makePayment(Long userId, Long price){

    }
}
