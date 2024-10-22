package com.project.concert_reservation.support.redis;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collection;

@Component
public class RedisDriver {
    private static RedissonClient redissonClient;

    public RedisDriver(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
        redissonClient = Redisson.create(config);
    }

    public boolean putSet(String key, Object value){
        return redissonClient.getSet(key).add(value);
    }

    public boolean putBatchSet(String key, Collection<Object> values){
        return redissonClient.getSet(key).addAll(values);
    }

    public boolean putScoredSortedSet(String key, Object value){
        Double newScore = redissonClient.getScoredSortedSet(key).addScore(redissonClient.getScoredSortedSet(key).lastScore(), 1);
        return redissonClient.getScoredSortedSet(key).add(newScore, value);
    }

    public Integer getRankOfScoredSortedSet(String key, Object value){
        return redissonClient.getScoredSortedSet(key).rank(value);
    }

    public Collection<Object> batchGetTopScoredSortedSet(String key, Integer getNum){
        return redissonClient.getScoredSortedSet(key).valueRange(0, getNum-1);
    }

    public Integer batchDeleteTopScoredSortedSet(String key, Integer deleteNum){
        return redissonClient.getScoredSortedSet(key).removeRangeByRank(0, deleteNum-1);
    }

    public void setWithTTL(String key, Object value, int ttl){
        RBucket<Object> bucket = redissonClient.getBucket(key);
        bucket.set(value, Duration.ofSeconds(ttl));
    }

    public boolean batchSetWithTTL(String key, Collection<Object> values, int ttl){
        RSet<Object> set = redissonClient.getSet(key);
        boolean result = set.addAll(values);
        for (Object value: values) {
            setWithTTL(key, value, ttl);
        }
        return result;
    }

    public boolean validates(String key){
        RBucket<Object> bucket = redissonClient.getBucket(key);
        return bucket.isExists();
    }

    public boolean extendTTL(String key, int ttl){
        RBucket<Object> bucket = redissonClient.getBucket(key);
        if (bucket.isExists()){
            Object value = bucket.get();
            bucket.set(value, Duration.ofSeconds(ttl));
            return true;
        }
        return false;
    }

    public boolean removeValueOfSet(String key, Object value){
        RSet<Object> sets = redissonClient.getSet(key);
       if (!sets.remove(value)){
           LoggerFactory.getLogger(RedisDriver.class).warn("Remove value of set {} failed", key);
            return false;
       }
       return true;
    }

    public Integer getNumOfSet(String key){
        RSet<Object> sets = redissonClient.getSet(key);
        return sets.size();
    }
}