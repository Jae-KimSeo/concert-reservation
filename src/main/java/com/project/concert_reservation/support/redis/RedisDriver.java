package com.project.concert_reservation.support.redis;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Component
public class RedisDriver {
    private static RedissonClient redissonClient;

    public RedisDriver(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
        redissonClient = Redisson.create(config);
    }

    public boolean putScoredSortedSet(String key, Object value){
        Double newScore = redissonClient.getScoredSortedSet(key).addScore(redissonClient.getScoredSortedSet(key).lastScore(), 1);
        return redissonClient.getScoredSortedSet(key).add(newScore, value);
    }

    public Integer getRankOfScoredSortedSet(String key, Object value){
        return redissonClient.getScoredSortedSet(key).rank(value);
    }

    public Collection<Object> batchGetTopScoredSortedSet(String key, Integer getNum){
        return redissonClient.getScoredSortedSet(key).valueRange(0, getNum);
    }

    public Integer batchDeleteTopScoredSortedSet(String key, Integer deleteNum){
        return redissonClient.getScoredSortedSet(key).removeRangeByRank(0, deleteNum);
    }

    // Use Sorted Set Instead of map
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

    public boolean putBatchSet(String key, Collection<Object> values){
        return redissonClient.getSet(key).addAll(values);
    }
}