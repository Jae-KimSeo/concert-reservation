package com.project.concert_reservation.interfaces.queue.controller.dto;

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