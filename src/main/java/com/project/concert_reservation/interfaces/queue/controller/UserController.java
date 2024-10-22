package com.project.concert_reservation.interfaces.queue.controller;

import com.project.concert_reservation.application.queue.service.QueueService;
import com.project.concert_reservation.application.queue.service.UserService;
import com.project.concert_reservation.domain.queue.domain.Queue;
import com.project.concert_reservation.domain.queue.domain.User;
import com.project.concert_reservation.interfaces.queue.controller.dto.UserCreateRequest;
import com.project.concert_reservation.interfaces.queue.controller.dto.UserCreateResponse;
import com.project.concert_reservation.interfaces.queue.controller.dto.CheckQueueStatusResponse;
import com.project.concert_reservation.mapper.queue.UserMapper;

import com.project.concert_reservation.support.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final QueueService queueService;
    private final UserMapper userMapper;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/queue")
    public ResponseEntity<CheckQueueStatusResponse> checkQueueStatus(@RequestBody String requestBody) {
        if (requestBody.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> requestMap;
        String jwt;
        Long leftWaitingCount;
        CheckQueueStatusResponse response = new CheckQueueStatusResponse();

        try {
            requestMap = objectMapper.readValue(requestBody, Map.class);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Long userId = (Long)requestMap.get("userId");
        if (!userService.ValidateUser(userId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (Objects.equals(requestMap.get("jwt"), "")) {
            jwt = userService.CreateWaitingToken(userId);
            // 여기도 비즈니스 로직으로 묶을까?
            queueService.EnterQueue(userId);
            leftWaitingCount = queueService.GetUserLeftWaitingCount(userId);
            response.setJwt(jwt);
            response.setWaitingUserNum(leftWaitingCount);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            jwt = (String)requestMap.get("jwt");
            // 여기 아래 비즈니스로직으로
            Queue.QueueType queueType = queueService.CheckQueueTypeUserPositioned(userId);
            if (queueType == Queue.QueueType.WAITING) {
                response.setWaitingUserNum(queueService.GetUserLeftWaitingCount(userId));
            } else if (queueType == Queue.QueueType.ONGOING) {
                response.setWaitingUserNum(0L);
            } else {
                logger.error("current input jwt is expired. publish new token requested");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response.setJwt(jwt);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }

    }

    @PostMapping("/cheat")
    public ResponseEntity<UserCreateResponse> cheat_CreateUser(@RequestBody String requestBody) {
        try {
            Map<String, String> requestMap = objectMapper.readValue(requestBody, Map.class);
            String name = requestMap.get("name");

            UserCreateRequest userCreateRequest = new UserCreateRequest();
            userCreateRequest.setName(name);

            User user = userService.Cheat_CreateUser(userCreateRequest);

            UserCreateResponse response = userMapper.domainToDto(user);
            response.setJwt(jwtTokenProvider.createToken(1L, ""));

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}