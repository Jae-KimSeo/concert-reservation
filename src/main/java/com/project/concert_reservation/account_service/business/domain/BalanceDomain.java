package com.project.concert_reservation.account_service.business.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class BalanceDomain {
    private String userId;
    private Long balance;
}
