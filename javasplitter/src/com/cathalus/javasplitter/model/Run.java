package com.cathalus.javasplitter.model;

import java.util.LinkedList;

/**
 * Created by Raymond on 25.11.2015.
 */
public class Run {

    private LinkedList<Segment> segment = new LinkedList<>();
    private int attempts = 0;
    private String name;
    private String category;

    public Run(String name)
    {
        this.name = name;
    }

    public void addSegment(Segment s)
    {
        segment.add(s);
    }

    public LinkedList<Segment> getSegment() {
        return segment;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
