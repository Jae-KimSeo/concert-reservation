package com.project.concert_reservation.application.payment;

import com.project.concert_reservation.application.payment.service.PaymentService;
import com.project.concert_reservation.application.point.service.PointService;
import com.project.concert_reservation.application.queue.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentFacade {
    private final PaymentService paymentService;
    private final PointService pointService;
    private final QueueService queueService;

    public Long pay(Long userId, Long price){
        pointService.spendPoint(userId, price);
        return paymentService.makePayment(userId, price);
    }
}
