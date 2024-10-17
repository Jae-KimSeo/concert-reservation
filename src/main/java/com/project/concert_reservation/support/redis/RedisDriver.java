package com.project.concert_reservation.support.redis;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

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

    public void batchPutSetCacheWithTTL(String key, Collection<Object> values, int ttl){
        HashMap<Object, Duration> cacheMap = new HashMap<>();
        for (Object value : values) {
            cacheMap.put(value, Duration.ofSeconds(ttl));
        }
        redissonClient.getSetCache(key).addAll(cacheMap);
    }

    public void setKeyWithTTL(String key, Object value, int ttl){
        RBucket<Object> bucket = redissonClient.getBucket(key);
        bucket.set(value, Duration.ofSeconds(ttl));
    }

    public boolean exists(String key){
        RBucket<Object> bucket = redissonClient.getBucket(key);
        return bucket.isExists();
    }

    public void extendTTL(String key, int ttl){
        RBucket<Object> bucket = redissonClient.getBucket(key);
        if (bucket.isExists()){
            Object value = bucket.get();
            bucket.set(value, Duration.ofSeconds(ttl));
        }
    }

    // Use Sorted Set Instead of map -> why
    public void setValueWithTTL(String key, Object value, Long ttl){
        RMap<Object, Object> map = redissonClient.getMap(key);
        if (!map.containsKey(value)) {
            map.put(value, value);
        }
        redissonClient.getBucket("ttl:"+key).set("exists", Duration.ofSeconds(ttl));
    }

    public void deleteValueOfMap(String key, Object value){
        redissonClient.getMap(key).remove(value);
    }

    public Integer getTotalValueNumOfMap(String key){
        return redissonClient.getMap(key).size();
    }

}