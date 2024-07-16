package com.project.concert_reservation.user_service.interfaces.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.concert_reservation.user_service.business.domain.UserDomain;
import com.project.concert_reservation.user_service.business.service.UserService;
import com.project.concert_reservation.user_service.interfaces.controller.dto.UserCreateRequest;
import com.project.concert_reservation.user_service.interfaces.controller.dto.UserCreateResponse;
import com.project.concert_reservation.user_service.interfaces.controller.dto.UserResponse;
import com.project.concert_reservation.user_service.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/queue")
    public ResponseEntity<UserResponse> createQueueToken(@RequestBody String id) {
        if (id.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean tokenCreated = true;

        if (tokenCreated) {
            UserResponse response = new UserResponse();
            response.setJwt("mock-jwt-token");
            response.setWaitingUserNum(10); // Mock waiting user number
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }

    @PostMapping("/cheat")
    public ResponseEntity<UserCreateResponse> cheat_CreateUser(@RequestBody String requestBody) {
        try {
            Map<String, String> requestMap = objectMapper.readValue(requestBody, Map.class);
            String name = requestMap.get("name");

            UserCreateRequest userCreateRequest = new UserCreateRequest();
            userCreateRequest.setName(name);

            UserDomain userDomain = userService.Cheat_CreateUser(userCreateRequest);
            UserCreateResponse response = userMapper.domainToDto(userDomain);
            response.setJwt("mock-jwt-token");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}