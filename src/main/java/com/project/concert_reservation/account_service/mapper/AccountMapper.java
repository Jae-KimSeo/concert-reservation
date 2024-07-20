package com.project.concert_reservation.account_service.mapper;

import com.project.concert_reservation.account_service.business.domain.BalanceDomain;
import com.project.concert_reservation.account_service.infrastructure.entity.Balance;
import com.project.concert_reservation.account_service.interfaces.controller.dto.BalanceResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public BalanceDomain entityToDomain (Balance balance) {
        BalanceDomain balanceDomain = new BalanceDomain();

        balanceDomain.setUserId(balance.getUserId());
        balanceDomain.setBalance(balance.getBalance());

        return balanceDomain;
    }

    public Balance domainToEntity (BalanceDomain balanceDomain) {
        Balance balance = new Balance();

        balance.setUserId(balanceDomain.getUserId());
        balance.setBalance(balance.getBalance());

        return balance;
    }

    public BalanceResponse domainToResponse (BalanceDomain balanceDomain) {
        BalanceResponse response = new BalanceResponse();
        response.setBalance(balanceDomain.getBalance());

        return response;
    }
}
