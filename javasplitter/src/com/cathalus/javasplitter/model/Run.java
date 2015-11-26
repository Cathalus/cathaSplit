package com.cathalus.javasplitter.model;

import java.util.LinkedList;

/**
 * Created by Raymond on 25.11.2015.
 */

/**
 * Stores all the information of a run
 */
public class Run {

    /**
     * Contains all <code>Segment</code>s of the run
     */
    private LinkedList<Segment> segment = new LinkedList<>();
    /**
     * The number of attempts
     */
    private int attempts = 0;
    /**
     * Name of the <code>Run</code>
     */
    private String name;
    /**
     * Category of the <code>Run</code>
     */
    private String category;

    public Run(String name)
    {
        this.name = name;
    }

    /**
     * Adds a segment to the list of Segments
     * @param s Segment that is going to be added
     */
    public void addSegment(Segment s)
    {
        segment.add(s);
    }

    /**
     * @return Returns the list of all Segments of the Run
     */
    public LinkedList<Segment> getSegment() {
        return segment;
    }

    /**
     * @return Returns the number of Attempts
     */
    public int getAttempts() {
        return attempts;
    }

    /**
     * Sets the number of attempts
     * @param attempts
     */
    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    /**
     * @return Returns the name of the Run
     */
    public String getName() {
        return name;
    }

    /**
     * @return Returns the category of the Run
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the run
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
