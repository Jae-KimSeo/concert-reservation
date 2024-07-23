package com.project.concert_reservation.account_service.infrastructure.entity;

import com.project.concert_reservation.account_service.business.domain.BillStatus;
import com.project.concert_reservation.reservation_service.Infrastructure.entity.Reservation;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

public class Bill {
    private Long id;
    @OneToOne
    private Reservation reservation;
    private LocalDateTime createdAt;
    private BillStatus billStatus;
}
