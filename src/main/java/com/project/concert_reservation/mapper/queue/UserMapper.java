package com.project.concert_reservation.mapper.queue;

import com.project.concert_reservation.domain.queue.domain.User;
import com.project.concert_reservation.domain.queue.entity.UserEntity;
import com.project.concert_reservation.interfaces.queue.controller.dto.UserCreateRequest;
import com.project.concert_reservation.interfaces.queue.controller.dto.UserCreateResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User entityToDomain(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        User userDomain = new User();
        userDomain.setId(userEntity.getId());
        userDomain.setName(userEntity.getName());

        return userDomain;
    }

    public UserEntity domainToEntity(User userDomain) {
        if (userDomain == null){
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDomain.getId());
        userEntity.setName(userDomain.getName());

        return userEntity;
    }

    public User dtoToDomain(UserCreateRequest userCreateRequest) {
        if (userCreateRequest == null){
            return null;
        }

        User user = new User();
        user.setName(userCreateRequest.getName());

        return user;
    }

    public UserCreateResponse domainToDto(User user){
        if (user == null){
            return null;
        }

        UserCreateResponse userCreateResponse = new UserCreateResponse();
        userCreateResponse.setId(user.getId());
        userCreateResponse.setName(user.getName());

        return userCreateResponse;
    }
}
