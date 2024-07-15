package com.project.concert_reservation.user_service.interfaces.controller;

import com.project.concert_reservation.user_service.interfaces.controller.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

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