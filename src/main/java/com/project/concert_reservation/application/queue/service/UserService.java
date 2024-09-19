package com.project.concert_reservation.application.queue.service;

import com.project.concert_reservation.domain.queue.domain.Queue;
import com.project.concert_reservation.domain.queue.entity.QueueEntity;
import com.project.concert_reservation.support.jwt.JwtTokenProvider;
import com.project.concert_reservation.domain.queue.domain.User;
import com.project.concert_reservation.domain.queue.port.QueueRepository;
import com.project.concert_reservation.domain.queue.port.UserRepository;
import com.project.concert_reservation.interfaces.queue.controller.dto.UserCreateRequest;
import com.project.concert_reservation.mapper.queue.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final QueueRepository queueRepository;
    private final UserMapper userMapper;

    public User Cheat_CreateUser(UserCreateRequest userCreateRequest) {
            User user = userMapper.dtoToDomain(userCreateRequest);

            user.setId(1L);
            user.setName(userCreateRequest.getName());

            return userMapper.entityToDomain(userRepository.addUser(userMapper.domainToEntity(user)));
    }

    public boolean ValidateUser(Long userId) {
        return userRepository.existsById(userId);
    }

    public String CreateWaitingToken(Long userId) {
        return jwtTokenProvider.createToken(userId, "");
    }

    public void expireQueue(Long userId) {
        List<QueueEntity> queueEntities = queueRepository.findQueueByUserId(userId);
        for (QueueEntity queueEntity : queueEntities){
            if (queueEntity.getQueueType() == Queue.QueueType.ONGOING) {
                queueEntity.setQueueType(Queue.QueueType.None);
            }
            break;
        }
    }
}
