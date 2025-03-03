package com.project.concert_reservation.account_service.business;

import com.project.concert_reservation.account_service.business.domain.BalanceDomain;

public interface AccountService {
    BalanceDomain GetBalance(String userId);
    BalanceDomain ReChargeBalance(String userId, Long chargeAmount);
}
