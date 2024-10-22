package com.project.concert_reservation.application.payment;

import com.project.concert_reservation.application.concert.service.ConcertService;
import com.project.concert_reservation.application.payment.service.PaymentService;
import com.project.concert_reservation.application.point.service.PointService;
import com.project.concert_reservation.application.queue.service.QueueService;
import com.project.concert_reservation.domain.payment.dto.PaymentDto;
import com.project.concert_reservation.domain.payment.model.Payment;
import com.project.concert_reservation.domain.point.model.Point;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentFacade {
    private final PaymentService paymentService;
    private final PointService pointService;
    private final ConcertService concertService;
    private final QueueService queueService;

    public PaymentDto pay(Long userId, Long reservationId, Long price){
        // 토큰 인증
        Point leftPoint = pointService.spendPoint(userId, price);
        concertService.setPaidAt(reservationId);
        Payment payment = paymentService.makePayment(userId, reservationId);
        // 토큰 만료

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setId(payment.getId());
        paymentDto.setLeftPoint(leftPoint.getPoint());
        paymentDto.setPaymentStatus(payment.getPaymentStatus());

        return paymentDto;
    }
}
