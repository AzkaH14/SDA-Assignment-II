package utils;

import java.util.concurrent.atomic.AtomicInteger;

public class CircuitBreaker {
    private int threshold = 3;
    private AtomicInteger failureCount = new AtomicInteger(0);
    private boolean open = false;

    public boolean isAvailable() {
        return !open;
    }

    public void recordSuccess() {
        failureCount.set(0);
    }

    public void recordFailure() {
        if (failureCount.incrementAndGet() >= threshold) {
            open = true;
            System.out.println("Circuit is open due to repeated failures.");
        }
    }

    public void reset() {
        open = false;
        failureCount.set(0);
    }
}

