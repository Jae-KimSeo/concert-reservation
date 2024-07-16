package com.project.concert_reservation.user_service.business.service;

import com.project.concert_reservation.common.jwt.JwtConfig;
import com.project.concert_reservation.common.jwt.JwtTokenProvider;
import com.project.concert_reservation.common.jwt.JwtTokenValidator;
import com.project.concert_reservation.user_service.business.domain.UserDomain;
import com.project.concert_reservation.user_service.infrastructure.entity.User;
import com.project.concert_reservation.user_service.infrastructure.repository.UserJpaRepository;
import com.project.concert_reservation.user_service.infrastructure.repository.UserRepository;
import com.project.concert_reservation.user_service.interfaces.controller.dto.UserCreateRequest;
import com.project.concert_reservation.user_service.interfaces.controller.dto.UserCreateResponse;
import com.project.concert_reservation.user_service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final JwtConfig jwtConfig;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenValidator jwtTokenValidator;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(JwtConfig jwtConfig, JwtTokenProvider jwtTokenProvider, JwtTokenValidator jwtTokenValidator, UserRepository userRepository, UserMapper userMapper) {
        this.jwtConfig = jwtConfig;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtTokenValidator = jwtTokenValidator;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDomain Cheat_CreateUser(UserCreateRequest userCreateRequest) {
            UserDomain userDomain = userMapper.dtoToDomain(userCreateRequest);

            String userId = UUID.randomUUID().toString();
            userDomain.setId(userId);
            userDomain.setName(userCreateRequest.getName());

            return userMapper.entityToDomain(userRepository.addUser(userMapper.domainToEntity(userDomain)));
    }

    public String ValidateUser(String userId) {
        // TODO : Check User Table
        return "userId";
    }

    public String CreateWaitingToken(String userId) {
        return "";
    }
}
