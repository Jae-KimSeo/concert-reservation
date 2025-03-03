package com.project.concert_reservation.support.redis;

import com.project.concert_reservation.support.util.SerializerUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class RedisDriverTest {
    private static final Logger logger = LoggerFactory.getLogger(RedisDriverTest.class);

    @Spy
    private static RedisDriver redisDriver;

    @BeforeAll
    public static void setUp() {
        redisDriver = new RedisDriver();
    }

    @Test
    public void testPutScoredSortedSet() {
        boolean result = redisDriver.putScoredSortedSet("test", "testtt");
        assertTrue(result);
    }

    @Test
    public void testGetRankOfScoredSortedSet() {
        Integer rank = redisDriver.getRankOfScoredSortedSet("test", "test");
        assertEquals(Integer.valueOf(1), rank);
    }

    @Test
    public void testBatchGetTopScoredSortedSet() {
        Collection<Object> result = redisDriver.batchGetTopScoredSortedSet("test", 2);
        LoggerFactory.getLogger(RedisDriverTest.class).info("{}", result);
        assertEquals(2, result.size());
    }

    @Test
    public void testSetKeyWithTTL() {
        Collection<Object> result = redisDriver.batchGetTopScoredSortedSet("test", 2);
        redisDriver.putBatchSet("activate-test-token", result);
        for (Object elem : result) {
            redisDriver.setWithTTL("ttl : " + elem, elem, 60);
        }
    }

    @Test
    public void testExtendTTLOfExistToken() {
        String key = "ttl : test";
        String elem = "test";
        boolean result = false;
        redisDriver.setWithTTL(key, elem, 60);
        if (redisDriver.validates(key)) {
            result = redisDriver.extendTTL(key, 60);
        } else {
            LoggerFactory.getLogger(RedisDriverTest.class).info("{} isn't exist key", key);
        }
        assertTrue(result);
    }

    @Test
    public void testBatchSetWithTTL() {
        Collection<Object> values = redisDriver.batchGetTopScoredSortedSet("test", 2);
        boolean result = redisDriver.batchSetWithTTL("test", values, 60);
        assertTrue(result);
    }

    @Test
    public void testGetNumOfSet(){
        redisDriver.putSet("test", SerializerUtil.serializeValue("test1"));
        redisDriver.putSet("test", SerializerUtil.serializeValue("test2"));

        assertEquals(Integer.valueOf(2), redisDriver.getNumOfSet("test"));
    }

    @Test
    public void testRemoveValueOfSet(){
        redisDriver.putSet("test", SerializerUtil.serializeValue("test1"));
        redisDriver.putSet("test", SerializerUtil.serializeValue("test2"));
        redisDriver.removeValueOfSet("test", SerializerUtil.serializeValue("test2"));
        assertEquals(Integer.valueOf(1), redisDriver.getNumOfSet("test"));
    }
}
