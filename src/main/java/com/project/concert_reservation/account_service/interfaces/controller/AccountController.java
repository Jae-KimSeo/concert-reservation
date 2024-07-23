package com.project.concert_reservation.account_service.interfaces.controller;

import com.project.concert_reservation.account_service.business.AccountService;
import com.project.concert_reservation.account_service.business.domain.BalanceDomain;
import com.project.concert_reservation.account_service.interfaces.controller.dto.BalanceRequest;
import com.project.concert_reservation.account_service.interfaces.controller.dto.BalanceResponse;
import com.project.concert_reservation.account_service.interfaces.controller.dto.PaymentRequest;
import com.project.concert_reservation.account_service.interfaces.controller.dto.PaymentResponse;
import com.project.concert_reservation.account_service.mapper.AccountMapper;
import com.project.concert_reservation.reservation_service.Business.domain.ReservationDomain;
import com.project.concert_reservation.reservation_service.Business.domain.SeatDomain;
import com.project.concert_reservation.reservation_service.Business.service.ReservationService;
import com.project.concert_reservation.user_service.business.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final ReservationService reservationService;
    private final UserService userService;
    private final AccountMapper accountMapper;

    @PostMapping("/payments")
    public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest request) {
        // TODO : Get UserId from jwt
        var userId = "test-user-id";

        // TODO : Adopt pacade layer
        reservationService.setPaidAt(request.getReservationId());
        Long spendPoint = (-1) * request.getSeatPrice();
        accountService.ReChargeBalance(userId, spendPoint);
        userService.expireQueue(userId);

        // TODO : Set Bill Entity and add Table
        PaymentResponse response = new PaymentResponse();

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
