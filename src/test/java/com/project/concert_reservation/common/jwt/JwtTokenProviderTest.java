package com.project.concert_reservation.common.jwt;

import com.project.concert_reservation.support.jwt.JwtConfig;
import com.project.concert_reservation.support.jwt.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class JwtTokenProviderTest {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProviderTest.class);

    @Mock
    private JwtConfig jwtConfig;

    @InjectMocks
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SecretKey secretKey = new SecretKeySpec(new byte[64], "HmacSHA256");
        when(jwtConfig.getSecretKey()).thenReturn(secretKey);
        when(jwtConfig.getValidityInMilliseconds()).thenReturn(3600000L);
        logger.info("Setup complete with SecretKey and validityInMilliseconds");
    }

    @Test
    public void testCreateToken() {
        String token = jwtTokenProvider.createToken("user123", "payloadData");
        assertNotNull(token, "Generated token should not be null");
        logger.info("Success: generated token: {}", token);
    }
}