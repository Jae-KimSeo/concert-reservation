package com.project.concert_reservation.interfaces.payment.controller;

import com.project.concert_reservation.application.payment.PaymentFacade;
import com.project.concert_reservation.application.payment.service.PaymentService;
import com.project.concert_reservation.interfaces.payment.controller.dto.PaymentRequest;
import com.project.concert_reservation.interfaces.payment.controller.dto.PaymentResponse;
import com.project.concert_reservation.application.concert.service.ReservationService;
import com.project.concert_reservation.application.queue.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentFacade paymentFacade;

    @PostMapping("/payments")
    public ResponseEntity<PaymentResponse> makePayment(@RequestBody Long userId, @RequestBody Long seatPrice) {
        // TODO : Get UserId from jwt
        paymentFacade.processPay(userId, seatPrice);

        // TODO : Set Bill Entity and add Table
        PaymentResponse response = new PaymentResponse();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
