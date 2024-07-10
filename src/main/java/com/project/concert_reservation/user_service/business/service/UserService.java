package com.project.concert_reservation.user_service.business.service;

import lombok.RequiredArgsConstructor;
import com.project.concert_reservation.user_service.business.model.*;
import org.springframework.stereotype.Service;

public interface UserService {
    String ValidateUser(String UUID);
}
