package com.cathalus.javasplitter.events;

/**
 * Created by Raymond on 25.11.2015.
 */
public class TimeEvent {

    private long currentTime = 0;

    public TimeEvent(long currentTime)
    {
        this.currentTime = currentTime;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }
}
