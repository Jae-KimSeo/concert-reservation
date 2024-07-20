package com.project.concert_reservation.common.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
public class JwtConfig {

    private final SecretKey secretKey;
    private final long validityInMilliseconds;

    public JwtConfig(@Value("${jwt.secret}") String secret,
                     @Value("${jwt.expiration}") long validityInMilliseconds) {
        byte[] keyBytes = secret.getBytes();
        this.secretKey = new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256");
        this.validityInMilliseconds = validityInMilliseconds;
    }

    @Bean
    public SecretKey getSecretKey() {
        return secretKey;
    }

    @Bean
    public long getValidityInMilliseconds() {
        return validityInMilliseconds;
    }
}