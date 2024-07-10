package com.project.concert_reservation.common.jwt;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.crypto.SecretKey;

@Configurable
public class JwtConfig {

    @Value("${jwt.secret}")
    private SecretKey secretKey;

    @Value("${jwt.expiration}")
    private long validityInMilliseconds;

    @Bean
    public SecretKey getSecretKey() {
        return secretKey;
    }

    @Bean
    public long getValidityInMilliseconds() {
        return validityInMilliseconds;
    }
}