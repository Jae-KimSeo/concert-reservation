package com.project.concert_reservation.user_service.business.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Getter
@Setter
@Component
public class WaitingQueue extends QueueDomain {
    private final HashMap<String, Long> waitingPositionMap;

    public WaitingQueue(HashMap<String, Long> waitingPositionMap) {
        this.waitingPositionMap = waitingPositionMap;
    }

    public void enqueue(String elem, Long position) {
        super.enqueue(elem);
        waitingPositionMap.put(elem, position);
    }

    @Override
    public String dequeue() {
        String elem = super.dequeue();
        if (elem != null) {
            waitingPositionMap.remove(elem);
        }
        return elem;
    }

    @Override
    public void removeIndex(int index) {
        waitingPositionMap.remove(queue.remove(index));
    }

    public Long getWaitingCount(String userId) {
        return waitingPositionMap.get(userId) - waitingPositionMap.get(queue.peekFirst());
    }
}
