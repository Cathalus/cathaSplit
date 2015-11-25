package com.cathalus.javasplitter.model;

import java.util.LinkedList;

/**
 * Created by Raymond on 25.11.2015.
 */
public class Run {

    private LinkedList<Split> splits = new LinkedList<>();
    private int attempts = 0;
    private String name;

    public Run(String name)
    {
        this.name = name;
    }

    public void addSplit(Split s)
    {
        splits.add(s);
    }

    public LinkedList<Split> getSplits() {
        return splits;
    }

    public int getAttempts() {
        return attempts;
    }

    public String getName() {
        return name;
    }
}
