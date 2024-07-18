package com.project.concert_reservation.account_service.interfaces.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BalanceRequest {
    private String userId;
    private Long chargeAmount;
}
