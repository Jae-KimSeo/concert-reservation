package com.project.concert_reservation.user_service.business.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class QueueDomain {
    protected final LinkedList<String> queue = new LinkedList<>();
    private static final Logger logger = LoggerFactory.getLogger(WaitingQueue.class);

    public void enqueue(String elem) {
        if (queue.contains(elem)) {
            logger.info("Element {} is already exists in queue", elem);
            return;
        }
        queue.addLast(elem);
    }

    public String dequeue() {
        return queue.pollFirst();
    }

    public boolean exists(String elem) {
        return queue.contains(elem);
    }

    public int getSize() {
        return queue.size();
    }

    public void removeIndex(int Index) {
        queue.remove(Index);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public void enqueueBatch(List<String> userIds) {
        for (int i = 0; i < userIds.size(); i++) {
            enqueue(userIds.get(i));
        }
    }

    public List<String> dequeueBatch(int batchNum) {
        if (queue.size() < batchNum) {
            logger.error("Queue size is under request batchNum");
            return null;
        }
        List<String> userIdList = new ArrayList<>(batchNum);
        for (int i = 0; i < batchNum; i++) {
            userIdList.addLast(queue.pollFirst());
        }
        return userIdList;
    }
}
