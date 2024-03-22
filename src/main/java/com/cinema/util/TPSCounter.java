package com.cinema.util;

import lombok.Data;

@Data
public class TPSCounter {
    private long countTime;
    private int currentTps;

    public TPSCounter() {
        countTime = System.currentTimeMillis();
        currentTps = 0;
    }

    public synchronized void setCountTime(long countTime) {
        this.countTime = countTime;
    }

    public synchronized void setCurrentTps(int currentTps) {
        this.currentTps = currentTps;
    }

    public synchronized int increaseTps() {
        this.currentTps++;
        return this.currentTps;
    }

    public synchronized void decreaseTps() {
        this.currentTps--;
    }


}
