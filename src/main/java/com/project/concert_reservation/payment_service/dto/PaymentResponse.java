package com.project.concert_reservation.payment_service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class PaymentResponse {
    private String billId;
    private Long price;
    private String buyerId;
    private String scheduleId;
    private String seatId;
    private LocalDateTime createdAt;
}