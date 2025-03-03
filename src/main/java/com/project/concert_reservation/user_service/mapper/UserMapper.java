package com.project.concert_reservation.user_service.mapper;

import com.project.concert_reservation.user_service.business.domain.UserDomain;
import com.project.concert_reservation.user_service.infrastructure.entity.User;
import com.project.concert_reservation.user_service.interfaces.controller.dto.UserCreateRequest;
import com.project.concert_reservation.user_service.interfaces.controller.dto.UserCreateResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDomain entityToDomain(User user) {
        if (user == null) {
            return null;
        }

        UserDomain userDomain = new UserDomain();
        userDomain.setId(user.getId());
        userDomain.setName(user.getName());

        return userDomain;
    }

    public User domainToEntity(UserDomain userDomain) {
        if (userDomain == null){
            return null;
        }

        User user = new User();
        user.setId(userDomain.getId());
        user.setName(userDomain.getName());

        return user;
    }

    public UserDomain dtoToDomain(UserCreateRequest userCreateRequest) {
        if (userCreateRequest == null){
            return null;
        }

        UserDomain userDomain = new UserDomain();
        userDomain.setName(userCreateRequest.getName());

        return userDomain;
    }

    public UserCreateResponse domainToDto(UserDomain userDomain){
        if (userDomain == null){
            return null;
        }

        UserCreateResponse userCreateResponse = new UserCreateResponse();
        userCreateResponse.setId(userDomain.getId());
        userCreateResponse.setName(userDomain.getName());

        return userCreateResponse;
    }
}
