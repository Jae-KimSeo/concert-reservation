package com.project.concert_reservation.domain.queue.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class UserEntity {
    @Id
    Long id;
    String name;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
