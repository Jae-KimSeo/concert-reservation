package com.project.concert_reservation.interfaces.payment.controller;

import com.project.concert_reservation.application.payment.PaymentFacade;
import com.project.concert_reservation.interfaces.payment.controller.dto.PaymentResponse;
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
        Long billId = paymentFacade.pay(userId, seatPrice);

        // TODO : Set Bill Entity and add Table
        PaymentResponse response = new PaymentResponse();
        response.setBillId(billId);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
