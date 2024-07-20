package com.project.concert_reservation.account_service.business;

import com.project.concert_reservation.account_service.business.domain.BalanceDomain;
import com.project.concert_reservation.account_service.infrastructure.BalanceRepository;
import com.project.concert_reservation.account_service.infrastructure.entity.Balance;
import com.project.concert_reservation.account_service.mapper.AccountMapper;
import com.project.concert_reservation.user_service.business.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceTest.class);

    @Spy
    private BalanceRepository balanceRepository;

    @Spy
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("잔액 확인 테스트")
    public void testGetBalance() {
        var userId = "user1";
        var initialBalance = new Balance();
        initialBalance.setBalance(1000L);
        Optional<Balance> optionalBalance = Optional.of(initialBalance);

        var balanceDomain = new BalanceDomain();
        balanceDomain.setBalance(1000L);

        when(balanceRepository.findBalanceByUserId(userId)).thenReturn(optionalBalance);
        when(accountMapper.entityToDomain(initialBalance)).thenReturn(balanceDomain);

        BalanceDomain result = accountService.GetBalance(userId);
        assertEquals(1000L, result.getBalance());
        logger.info("Test Passed: Balance is equal to initial balance amount");
    }


    @Test
    @DisplayName("잔액 충전 테스트 - 기존 잔액 없음")
    public void testReChargeBalance_NoExistingBalance() {
        var userId = "user2";
        Optional<Balance> optionalBalance = Optional.empty();

        var newBalance = new Balance();
        newBalance.setBalance(1000L);

        var balanceDomain = new BalanceDomain();
        balanceDomain.setBalance(1000L);

        when(balanceRepository.findBalanceByUserId(userId)).thenReturn(optionalBalance);
        when(balanceRepository.addBalance(userId, 1000L)).thenReturn(newBalance);
        when(accountMapper.entityToDomain(newBalance)).thenReturn(balanceDomain);

        BalanceDomain result = accountService.ReChargeBalance(userId, 1000L);
        assertEquals(1000L, result.getBalance());
        logger.info("Test Passed: Balance is added correctly for new balance");
    }
}
