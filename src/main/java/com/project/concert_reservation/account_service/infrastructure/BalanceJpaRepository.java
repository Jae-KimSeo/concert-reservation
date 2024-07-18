package com.project.concert_reservation.account_service.infrastructure;

import com.project.concert_reservation.account_service.infrastructure.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceJpaRepository extends JpaRepository<Balance, Long> {
    Optional<Balance> findByUserId(String userId);
    boolean existsByUserId(String userId);
}
