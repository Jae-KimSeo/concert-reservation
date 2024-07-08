package com.project.concert_reservation.user_service.controller;


import com.project.concert_reservation.user_service.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Service", description = "유저 대기열 API")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Operation(
            summary = "사용자를 대기열 큐에 등록",
            description = "사용자가 현재 서비스 이용가능한지 확인하고 불가시 대기열 토큰을 발행해 대기열에 진입시킴",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "해당 사용자에 대해 대기열 토큰 발행."
                    )
            }
    )
    @PostMapping("/queue")
    public ResponseEntity<UserResponse> createQueueToken(@RequestBody String uuid) {
        if (uuid.isEmpty()) {
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
}