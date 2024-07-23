package com.project.concert_reservation.account_service.business;

import com.project.concert_reservation.account_service.business.domain.BalanceDomain;
import com.project.concert_reservation.account_service.infrastructure.BalanceRepository;
import com.project.concert_reservation.account_service.infrastructure.entity.Balance;
import com.project.concert_reservation.account_service.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final BalanceRepository balanceRepository;
    private final AccountMapper accountMapper;

    public BalanceDomain GetBalance(String userId){
        Optional<Balance> balance = balanceRepository.findBalanceByUserId(userId);
        if (balance.isPresent()) {
          return accountMapper.entityToDomain(balance.get());
        } else {
            // TODO : Add log for balance table create
            return accountMapper.entityToDomain(balanceRepository.addBalance(userId, 0L));
        }
    }

    // TODO : Change Method to name to contain spend case
    public BalanceDomain ReChargeBalance(String userId, Long chargeAmount){
        Optional<Balance> balance = balanceRepository.findBalanceByUserId(userId);
        if (balance.isPresent()) {
            return accountMapper.entityToDomain(balanceRepository.updateBalance(userId, balance.get().getBalance() + chargeAmount));
        } else {
            return accountMapper.entityToDomain(balanceRepository.addBalance(userId, chargeAmount));
        }
    }

}
