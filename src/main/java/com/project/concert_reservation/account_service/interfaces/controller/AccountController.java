package com.project.concert_reservation.account_service.interfaces.controller;

import com.project.concert_reservation.account_service.business.AccountService;
import com.project.concert_reservation.account_service.business.domain.BalanceDomain;
import com.project.concert_reservation.account_service.interfaces.controller.dto.BalanceRequest;
import com.project.concert_reservation.account_service.interfaces.controller.dto.BalanceResponse;
import com.project.concert_reservation.account_service.interfaces.controller.dto.PaymentRequest;
import com.project.concert_reservation.account_service.interfaces.controller.dto.PaymentResponse;
import com.project.concert_reservation.account_service.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final Map<String, Long> userBalances = new HashMap<>();

    @PostMapping("/payments")
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
    public ResponseEntity<BalanceResponse> getOrUpdateBalance(@RequestBody BalanceRequest request) {
        if (request.getUserId().isEmpty() ||
                request.getChargeAmount() <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BalanceResponse response = accountMapper.domainToResponse(accountService.ReChargeBalance(request.getUserId(), request.getChargeAmount()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
