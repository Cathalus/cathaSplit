package com.cathalus.javasplitter.presenter;

import com.cathalus.javasplitter.view.Display;

import java.util.ArrayList;

/**
 * Created by Cathalus on 24/11/2015.
 */
public abstract class Presenter {
    protected ArrayList<Display> displays;

    public abstract void start();
    public abstract void bind();
    public void setDisplays(ArrayList<Display> displays)
    {
        this.displays = displays;
    }
}
