package com.project.concert_reservation.user_service.business.service;

import com.project.concert_reservation.user_service.business.domain.UserDomain;
import com.project.concert_reservation.user_service.interfaces.controller.dto.UserCreateRequest;
import org.springframework.stereotype.Service;

public interface UserService {
    UserDomain Cheat_CreateUser(UserCreateRequest userCreateRequest);
    boolean ValidateUser(String userId);
    String CreateWaitingToken(String userId);
}
