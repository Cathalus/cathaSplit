package com.cathalus.javasplitter.events;

/**
 * Created by Raymond on 25.11.2015.
 */

/**
 * An Event that informs the listeners that time has passed
 */
public class TimeEvent {

    /**
     * Stores the time of dispatch in nano seconds
     */
    private long currentTime = 0;

    public TimeEvent(long currentTime)
    {
        this.currentTime = currentTime;
    }

    /**
     * @return Returns the stored time
     */
    public long getCurrentTime() {
        return currentTime;
    }
}
