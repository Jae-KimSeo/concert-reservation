package com.project.concert_reservation.user_service.business.service;

public interface UserService {
    String ValidateUser(String userId);
    String CreateWaitingToken(String UserId);
}
