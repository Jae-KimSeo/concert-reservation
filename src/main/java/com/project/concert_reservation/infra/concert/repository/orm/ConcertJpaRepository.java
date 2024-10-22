package com.project.concert_reservation.infra.concert.repository.orm;

import com.project.concert_reservation.domain.concert.entity.ConcertEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertJpaRepository extends JpaRepository<ConcertEntity, Long> {
}
