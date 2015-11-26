package com.cathalus.javasplitter;

import com.cathalus.javasplitter.model.Run;
import com.cathalus.javasplitter.model.Segment;

import java.util.LinkedList;

/**
 * Created by Raymond on 26.11.2015.
 */

/**
 * Controls attributes of the current <code>Run</code>.
 */
public class RunController {

    /**
     *
     */
    private Run current;
    private int currentSplitIndex = 0;
    private boolean hasFinished = false;

    public RunController(Run run)
    {
        this.current = run;
    }

    public void nextSplit()
    {
        if(currentSplitIndex < current.getSegment().size()-1)
        {
            currentSplitIndex++;
        }else{
            hasFinished = true;
        }
    }

    public void previousSplit()
    {
        if(currentSplitIndex > 0)
        {
            currentSplitIndex--;
        }
    }

    public Segment getCurrent()
    {
        return current.getSegment().get(currentSplitIndex);
    }

    public int getCurrentIndex()
    {
        return currentSplitIndex;
    }

    public LinkedList<Segment> getSplits()
    {
        return current.getSegment();
    }

    public int getCurrentSplitIndex() {
        return currentSplitIndex;
    }

    public boolean hasFinished()
    {
        return hasFinished;
    }
}
