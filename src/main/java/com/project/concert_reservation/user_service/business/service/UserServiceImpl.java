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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenValidator jwtTokenValidator;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDomain Cheat_CreateUser(UserCreateRequest userCreateRequest) {
            UserDomain userDomain = userMapper.dtoToDomain(userCreateRequest);

            String userId = UUID.randomUUID().toString();
            userDomain.setId(userId);
            userDomain.setName(userCreateRequest.getName());

            return userMapper.entityToDomain(userRepository.addUser(userMapper.domainToEntity(userDomain)));
    }

    public boolean ValidateUser(String userId) {
        return userRepository.existsByUserId(userId);
    }

    public String CreateWaitingToken(String userId) {
        return jwtTokenProvider.createToken(userId, "");
    }

    public String ParseWaitingToken(String jwt){
        return jwtTokenValidator.getUsername(jwt);
    }
}
