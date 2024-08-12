package com.project.concert_reservation.interfaces.payment.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.concert_reservation.application.payment.PaymentFacade;
import com.project.concert_reservation.domain.payment.dto.PaymentDto;
import com.project.concert_reservation.interfaces.payment.controller.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentFacade paymentFacade;
    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<PaymentResponse> makePayment(@RequestBody String requestBody) {
        if (requestBody.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> requestMap;

        try {
            requestMap = objectMapper.readValue(requestBody, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // TODO : Get UserId from jwt
        PaymentDto paymentDto = paymentFacade.pay((convertToLong(requestMap.get("userId"))), convertToLong(requestMap.get("reservationId")), convertToLong(requestMap.get("seatPrice")));
        PaymentResponse response = new PaymentResponse();
        response.setId(paymentDto.getId());
        response.setLeftPoint(paymentDto.getLeftPoint());
        response.setPaymentStatus(paymentDto.getPaymentStatus());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private Long convertToLong(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Value is null");
        }
        if (value instanceof Number) {
            return ((Number) value).longValue();
        } else {
            throw new ClassCastException("Value is not a number");
        }
    }
}
