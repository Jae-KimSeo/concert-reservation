package com.project.concert_reservation.domain.queue.domain;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Getter
@Component
public class OngoingQueue extends QueueDomain {

    private final int capacity;

    public OngoingQueue(@Value("${ongoing.queue.capacity}") int capacity) {
        super();
        this.capacity = capacity;
    }

    @Override
    public void enqueue(String userId) {
        if (queue.size() >= capacity) {
            throw new ArrayIndexOutOfBoundsException("Ongoing Queue capacity exceed");
        } else {
            super.enqueue(userId);
        }
    }

    public boolean isFull() {
        return queue.size() == capacity;
    }
}
