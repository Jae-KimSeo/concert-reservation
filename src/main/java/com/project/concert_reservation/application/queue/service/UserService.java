package com.project.concert_reservation.application.queue.service;

import com.project.concert_reservation.domain.queue.domain.UserDomain;
import com.project.concert_reservation.interfaces.queue.controller.dto.UserCreateRequest;

public interface UserService {
    UserDomain Cheat_CreateUser(UserCreateRequest userCreateRequest);
    boolean ValidateUser(String userId);
    String CreateWaitingToken(String userId);
    void expireQueue(String userId);
}
