package com.project.concert_reservation.payment_service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BalanceResponse {
    private Long balance;
}