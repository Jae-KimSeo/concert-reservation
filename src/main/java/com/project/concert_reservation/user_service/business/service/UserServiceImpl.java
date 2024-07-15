package com.project.concert_reservation.user_service.business.service;

import com.project.concert_reservation.common.jwt.JwtConfig;
import com.project.concert_reservation.common.jwt.JwtTokenProvider;
import com.project.concert_reservation.common.jwt.JwtTokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final JwtConfig jwtConfig;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenValidator jwtTokenValidator;

    @Autowired
    public UserServiceImpl(JwtConfig jwtConfig, JwtTokenProvider jwtTokenProvider, JwtTokenValidator jwtTokenValidator) {
        this.jwtConfig = jwtConfig;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtTokenValidator = jwtTokenValidator;
    }

    public String ValidateUser(String userId) {
        // TODO : Check User Table
        return "userId";
    }

    public String CreateWaitingToken(String userId) {
        return "";
    }
}
