package com.project.concert_reservation.account_service.interfaces.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PaymentRequest {
    private String scheduleId;
    private String seatId;
    private Long price;
}
