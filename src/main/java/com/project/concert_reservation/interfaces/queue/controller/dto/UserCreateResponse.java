package com.project.concert_reservation.interfaces.queue.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateResponse {
    Long id;
    String name;

    String jwt;
}
