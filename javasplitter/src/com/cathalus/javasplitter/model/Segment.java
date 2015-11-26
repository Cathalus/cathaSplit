package com.cathalus.javasplitter.model;

/**
 * Created by Raymond on 25.11.2015.
 */

/**
 * Describes a Segment of a run
 */
public class Segment {

    /**
     * Current time of the segment in milliseconds (from start)
     */
    int currentTime = 0;
    /**
     * Best time of the segment in milliseconds (from start)
     */
    private int bestTime;
    /**
     * Difference between currentTime and bestTime
     */
    private int difference = 0;
    /**
     * Name of the segment
     */
    private String name;

    public Segment(String name, int bestTime)
    {
        this.name = name;
        this.bestTime = bestTime;
    }

    public int getBestTime() {
        return bestTime;
    }

    public void setBestTime(int bestTime) {
        this.bestTime = bestTime;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.difference = currentTime-bestTime;
        this.currentTime = currentTime;
    }
}
