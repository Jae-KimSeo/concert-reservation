package com.project.concert_reservation.account_service.infrastructure;

import com.project.concert_reservation.account_service.infrastructure.entity.Balance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BalanceRepositoryImpl implements BalanceRepository {

    private final BalanceJpaRepository balanceJpaRepository;

    public Balance addBalance(String userId, Long chargeAmount){
        Optional<Balance> balance = findBalanceByUserId(userId);

        if (balance.isEmpty()) {
            Balance newBalance = new Balance();
            newBalance.setUserId(userId);
            newBalance.setBalance(chargeAmount);

            return balanceJpaRepository.save(newBalance);
        }
        // TODO : Add error log when balance is exists case with given userID

        return null;
    }

    public Balance updateBalance(String userId, Long updatedBalance){
        Optional<Balance> balance = findBalanceByUserId(userId);

        if (balance.isPresent()) {
            Balance newBalance = new Balance();
            newBalance.setUserId(userId);
            newBalance.setBalance(updatedBalance);

            return balanceJpaRepository.save(newBalance);
        }
        // TODO : Add error log when balance is not exists with given userID

        return null;
    }

    public Optional<Balance> findBalanceByUserId(String userId){
        return balanceJpaRepository.findByUserId(userId);
    }

    public boolean existsByUserId(String userId){
        return balanceJpaRepository.existsByUserId(userId);
    }
}
