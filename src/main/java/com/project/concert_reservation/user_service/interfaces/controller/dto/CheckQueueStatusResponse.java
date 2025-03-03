package com.project.concert_reservation.user_service.interfaces.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class CheckQueueStatusResponse {
    private String jwt;
    private Long waitingUserNum;
}