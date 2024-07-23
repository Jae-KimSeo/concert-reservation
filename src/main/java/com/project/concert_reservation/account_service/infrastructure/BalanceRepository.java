package com.project.concert_reservation.account_service.infrastructure;

import com.project.concert_reservation.account_service.infrastructure.entity.Balance;

import java.util.Optional;

public interface BalanceRepository {
    Balance addBalance(String userId, Long chargeAmount);
    Balance updateBalance(String userId, Long chargeAmount);
    Optional<Balance> findBalanceByUserId(String userId);
    boolean existsByUserId(String userId);
    
}
