package com.project.concert_reservation.infra.queue.repository;

import com.project.concert_reservation.domain.queue.entity.QueueEntity;
import com.project.concert_reservation.domain.queue.port.QueueRepository;
import com.project.concert_reservation.support.redis.RedisDriver;
import com.project.concert_reservation.support.util.SerializerUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@RequiredArgsConstructor
@Repository
public class QueueRedisRepository implements QueueRepository {
    private final RedisDriver redisDriver;

    public static final String WaitingQueue = "WaitingQueue";
    public static final String OngoingQueue = "OngoingQueue";
    public static final Long TokenTTL = 300L;

    public void enterQueue(QueueEntity queueEntity){
        String serializedValue = SerializerUtil.serializeValue(queueEntity.getUserId());
        redisDriver.putScoredSortedSet(WaitingQueue, serializedValue);
    }

    public Integer getQueueRankById(Long userId){
        return redisDriver.getRankOfScoredSortedSet(WaitingQueue, SerializerUtil.serializeValue(userId));
    }

    public void activateQueueTokens(){
        //TODO : how to handle concurrency problem?
       // Collection<Object> tokens = redisDriver.batchGetTopScoredSortedSet(WaitingQueue, redisDriver.getTotalValueNumOfMap(OngoingQueue));
       // redisDriver.batchDeleteTopScoredSortedSet(WaitingQueue, tokens.size());
       // if (!redisDriver.putBatchSet(OngoingQueue, tokens)) {
         //   LoggerFactory.getLogger(QueueRedisRepository.class).error("Failed to activate queue");
        //}
    }

    public void extendActivateTokenExpiration(Long userId){
        //redisDriver.setValueWithTTL(OngoingQueue, SerializerUtil.serializeValue(userId), TokenTTL);
    }

    public void expireActivateToken(Long userId){
        //redisDriver.deleteValueOfMap(OngoingQueue, SerializerUtil.serializeValue(userId));
    }

    public void expireUnActivatedTokens(){
        // from Activated Set, check each Token By TTL
        // 이거 TTL 만료시에 알아서 삭제되나? TTL 연장/재생성되는 메소드가 필요할듯?
        // TTL에 대한 리서치
        // 지금 문제는 ttl이 Sorted

    }
}
