package com.project.concert_reservation.user_service.infrastructure.entity;

import com.project.concert_reservation.account_service.business.domain.BillStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class User {
    @Id
    String id;
    String name;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    BillStatus billStatus;
}
