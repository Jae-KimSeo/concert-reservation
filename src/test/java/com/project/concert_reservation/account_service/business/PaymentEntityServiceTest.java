package com.project.concert_reservation.account_service.business;

import com.project.concert_reservation.domain.point.port.PointRepository;
import com.project.concert_reservation.mapper.payment.PaymentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PaymentEntityServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(PaymentEntityServiceTest.class);

    @Spy
    private PointRepository pointRepository;

    @Spy
    private PaymentMapper paymentMapper;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    @DisplayName("잔액 확인 테스트")
//    public void testGetBalance() {
//        var userId = "user1";
//        var initialBalance = new Balance();
//        initialBalance.setBalance(1000L);
//        Optional<Balance> optionalBalance = Optional.of(initialBalance);
//
//        var balanceDomain = new BalanceDomain();
//        balanceDomain.setBalance(1000L);
//
//        when(pointRepository.findBalanceByUserId(userId)).thenReturn(optionalBalance);
//        when(accountMapper.entityToDomain(initialBalance)).thenReturn(balanceDomain);
//
//        BalanceDomain result = accountService.GetBalance(userId);
//        assertEquals(1000L, result.getBalance());
//        logger.info("Test Passed: Balance is equal to initial balance amount");
//    }


//    @Test
//    @DisplayName("잔액 충전 테스트 - 기존 잔액 없음")
//    public void testReChargeBalance_NoExistingBalance() {
//        var userId = "user2";
//        Optional<Balance> optionalBalance = Optional.empty();
//
//        var newBalance = new Balance();
//        newBalance.setBalance(1000L);
//
//        var balanceDomain = new BalanceDomain();
//        balanceDomain.setBalance(1000L);
//
//        when(pointRepository.findBalanceByUserId(userId)).thenReturn(optionalBalance);
//        when(pointRepository.addBalance(userId, 1000L)).thenReturn(newBalance);
//        when(accountMapper.entityToDomain(newBalance)).thenReturn(balanceDomain);
//
//        BalanceDomain result = accountService.ReChargeBalance(userId, 1000L);
//        assertEquals(1000L, result.getBalance());
//        logger.info("Test Passed: Balance is added correctly for new balance");
//    }
}
