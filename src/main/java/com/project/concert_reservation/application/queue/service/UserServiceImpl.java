package com.project.concert_reservation.application.queue.service;

import com.project.concert_reservation.support.jwt.JwtTokenProvider;
import com.project.concert_reservation.domain.queue.domain.QueueType;
import com.project.concert_reservation.domain.queue.domain.UserDomain;
import com.project.concert_reservation.domain.queue.entity.Queue;
import com.project.concert_reservation.infra.queue.repository.QueueRepository;
import com.project.concert_reservation.infra.queue.repository.UserRepository;
import com.project.concert_reservation.interfaces.queue.controller.dto.UserCreateRequest;
import com.project.concert_reservation.mapper.queue.UserMapper;
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
