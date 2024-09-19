package com.project.concert_reservation.support.redis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class RedisDriverTest {
    private static final Logger logger = LoggerFactory.getLogger(RedisDriverTest.class);

    @Spy
    private RedisDriver redisDriver;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        redisDriver = new RedisDriver();
    }

    @Test
    public void testPutScoredSortedSet() {
        boolean result = redisDriver.putScoredSortedSet("test", "test");
        assertTrue(result);
    }
}
