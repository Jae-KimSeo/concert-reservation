package com.project.concert_reservation.account_service.business.domain;

import com.project.concert_reservation.reservation_service.Business.domain.ReservationDomain;

public class BillDomain {
    private Long id;
    private Long price;
    private ReservationDomain reservationDomain;
    private BillStatus billStatus;
}
