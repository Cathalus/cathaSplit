package com.cathalus.javasplitter.model;

/**
 * Created by Raymond on 25.11.2015.
 */
public class Split {

    private int current = -1;
    private int miliseconds;
    private int difference = -1;
    private String name;

    public Split(String name, int miliseconds)
    {
        this.name = name;
        this.miliseconds = miliseconds;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getMiliseconds() {
        return miliseconds;
    }

    public void setMiliseconds(int miliseconds) {
        this.miliseconds = miliseconds;
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
}
