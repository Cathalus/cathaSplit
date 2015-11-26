package com.cathalus.javasplitter;

import com.cathalus.javasplitter.events.HotkeyEvent;
import com.cathalus.javasplitter.events.HotkeyEventListener;
import com.cathalus.javasplitter.events.TimeEvent;
import com.cathalus.javasplitter.events.TimeEventListener;
import com.cathalus.javasplitter.model.Hotkey;
import com.cathalus.javasplitter.util.Globals;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Raymond on 25.11.2015.
 */

/**
 * Controls Time and Timing of a Run
 */
public class TimeController implements HotkeyEventListener {

    /**
     * Timer that handles timing the run
     */
    private Timer timer;
    /**
     * Task that gets called when the timer fires
     */
    private TimerTask ticker;
    /**
     * Refresh period of the timer
     */
    private int period;
    /**
     * Set of TimeEventListeners
     */
    private HashSet<TimeEventListener> listeners = new HashSet<>();
    /**
     * Current state of the timer
     */
    private boolean isRunning = false;
    /**
     * Current state of the timer
     */
    private long lastSplit = 0;

    public TimeController(int period)
    {
        this.period = period;
        timer = new Timer(true);
        JavaSplitter.HotkeyHandler.addHotkeyEventListener(this);
    }

    /**
     * Dispatches a TimerEvent to all listeners
     * @param e TimerEvent
     */
    private void dispatch(TimeEvent e)
    {
        for(TimeEventListener listener : listeners)
        {
            listener.onTimeEvent(e);
        }
    }

    @Override
    public void handleHotkeyEvent(HotkeyEvent e) {
        Hotkey hotkey = e.getHotkey();
        long current = System.nanoTime();
        switch (hotkey) {
            case START:
                if (!isRunning && !JavaSplitter.RunController.hasFinished()) {
                    if (ticker != null)
                        ticker.cancel();
                    isRunning = true;
                    Globals.START = current;
                    ticker = new TimerTask() {
                        @Override
                        public void run() {
                            long currentTime = System.nanoTime();
                            checkIfFinished();
                            dispatch(new TimeEvent(currentTime));
                        }
                    };
                    timer.schedule(ticker, 0, this.period);
                }
                break;
            case RESET:
                if(ticker != null)
                    ticker.cancel();
                isRunning = false;
                dispatch(new TimeEvent(Globals.START));
                break;
            case SPLIT:
                lastSplit = System.nanoTime()-Globals.START;
                checkIfFinished();
                break;
        }
    }

    @Override
    public int getPriority() {
        return Globals.HK_PRIORITY_TIMECONTROLLER;
    }

    /**
     * Checks if the run has finished and if true cancels the timer
     */
    public void checkIfFinished()
    {
        if(JavaSplitter.RunController.hasFinished())
        {
            if (ticker != null)
                ticker.cancel();
            Globals.END = System.nanoTime();
            isRunning = false;
        }
    }

    /**
     * @return Returns if the timer is currently running
     */
    public boolean isRunning()
    {
        return isRunning;
    }

    /**
     * Adds a TimeEventListener to set of listeners
     * @param listener Listener
     */
    public void addTimeEventListener(TimeEventListener listener)
    {
        listeners.add(listener);
    }

    /**
     * @return Returns the timestamp of the last split
     */
    public long getLastSplit() {
        return lastSplit;
    }
}
