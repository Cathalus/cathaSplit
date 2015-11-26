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
     * Reference to the current <code>Run</code>
     */
    private Run current;
    /**
     * Index of the currently active Segment
     */
    private int currentSegmentIndex = 0;
    /**
     * True if the run has finished
     */
    private boolean hasFinished = false;

    public RunController(Run run)
    {
        this.current = run;
    }

    /**
     * Splits, stores split time and advances to the next segment
     * @param time
     */
    public void split(int time)
    {
        current.getSegment(currentSegmentIndex).setCurrentTime(time);
        nextSplit();
    }

    /**
     * Increases the index of the current segment.
     * Sets the run to finished if the last segment was the last one
     */
    public void nextSplit()
    {
        if(currentSegmentIndex < current.getSegments().size()-1)
        {
            currentSegmentIndex++;
        }else{
            hasFinished = true;
        }
    }

    /**
     * Decreases the index of the current segment.
     */
    public void previousSplit()
    {
        if(currentSegmentIndex > 0)
        {
            currentSegmentIndex--;
        }
    }

    /**
     * @return Returns the currently active segment
     */
    public Segment getCurrent()
    {
        return current.getSegments().get(currentSegmentIndex);
    }

    /**
     * @return Returns the index of the currently active segment
     */
    public int getCurrentIndex()
    {
        return currentSegmentIndex;
    }

    /**
     * @return Returns a list of all Segments
     */
    public LinkedList<Segment> getSegments()
    {
        return current.getSegments();
    }

    /**
     * @return Returns the index of the current segment
     */
    public int getCurrentSegmentIndex() {
        return currentSegmentIndex;
    }

    /**
     * @return Returns true if the run has finished
     */
    public boolean hasFinished()
    {
        return hasFinished;
    }

    public void reset()
    {
        currentSegmentIndex = 0;
        hasFinished = false;
        current.reset();
    }
}
