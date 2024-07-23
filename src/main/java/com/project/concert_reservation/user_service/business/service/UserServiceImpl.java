package com.project.concert_reservation.user_service.business.service;

import com.project.concert_reservation.common.jwt.JwtTokenProvider;
import com.project.concert_reservation.common.jwt.JwtTokenValidator;
import com.project.concert_reservation.user_service.business.domain.QueueType;
import com.project.concert_reservation.user_service.business.domain.UserDomain;
import com.project.concert_reservation.user_service.infrastructure.entity.Queue;
import com.project.concert_reservation.user_service.infrastructure.repository.QueueRepository;
import com.project.concert_reservation.user_service.infrastructure.repository.UserRepository;
import com.project.concert_reservation.user_service.interfaces.controller.dto.UserCreateRequest;
import com.project.concert_reservation.user_service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final QueueRepository queueRepository;
    private final UserMapper userMapper;

    public UserDomain Cheat_CreateUser(UserCreateRequest userCreateRequest) {
            UserDomain userDomain = userMapper.dtoToDomain(userCreateRequest);

            String userId = UUID.randomUUID().toString();
            userDomain.setId(userId);
            userDomain.setName(userCreateRequest.getName());

            return userMapper.entityToDomain(userRepository.addUser(userMapper.domainToEntity(userDomain)));
    }

    public boolean ValidateUser(String userId) {
        return userRepository.existsById(userId);
    }

    public String CreateWaitingToken(String userId) {
        return jwtTokenProvider.createToken(userId, "");
    }

    public void expireQueue(String userId) {
        List<Queue> queues = queueRepository.findQueueByUserId(userId);
        for (Queue queue : queues){
            if (queue.getQueueType() == QueueType.ONGOING) {
                queue.setQueueType(QueueType.None);
            }
            break;
        }
    }
}
