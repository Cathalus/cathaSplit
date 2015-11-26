package com.cathalus.javasplitter.events;

/**
 * Created by Raymond on 25.11.2015.
 */
public interface TimeEventListener {

    /**
     * Gets called when a <code>TimeEvent</code> is dispatched
     * @param e
     */
    public void onTimeEvent(TimeEvent e);

}
