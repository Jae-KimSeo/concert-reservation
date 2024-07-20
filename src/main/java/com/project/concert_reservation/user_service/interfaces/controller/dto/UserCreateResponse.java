package com.project.concert_reservation.user_service.interfaces.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateResponse {
    String id;
    String name;

    String jwt;
}
