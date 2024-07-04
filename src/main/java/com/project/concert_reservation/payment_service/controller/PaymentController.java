package com.project.concert_reservation.payment_service.controller;

import com.project.concert_reservation.payment_service.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final Map<String, Long> userBalances = new HashMap<>();

    @PostMapping()
    public ResponseEntity<PaymentResponse> makePayment(@RequestHeader("Authorization") String authorization,
                                                       @RequestBody PaymentRequest request) {
        if (request.getScheduleId().isEmpty() ||
                request.getSeatId().isEmpty() ||
                request.getPrice() <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!authorization.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String userId = "mockUserId";

        long currentBalance = userBalances.getOrDefault(userId, 10000L);
        if (currentBalance < request.getPrice()) {
            return new ResponseEntity<>(HttpStatus.PAYMENT_REQUIRED);
        }

        userBalances.put(userId, currentBalance - request.getPrice());

        PaymentResponse response = new PaymentResponse();
        response.setBillId(UUID.randomUUID().toString());
        response.setPrice(request.getPrice());
        response.setBuyerId(userId);
        response.setScheduleId(request.getScheduleId());
        response.setSeatId(request.getSeatId());
        response.setCreatedAt(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/balances")
    public ResponseEntity<BalanceResponse> updateBalance(@RequestBody BalanceRequest request) {
        if (request.getUserId().isEmpty() ||
                request.getBalanceAdjustment() <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        long currentBalance = userBalances.getOrDefault(request.getUserId(), 0L);
        long newBalance = currentBalance + request.getBalanceAdjustment();
        userBalances.put(request.getUserId(), newBalance);

        BalanceResponse response = new BalanceResponse();
        response.setBalance(newBalance);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
