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
public class TimeController implements HotkeyEventListener {

    private Timer timer;
    private TimerTick ticker;
    private int period;
    private HashSet<TimeEventListener> listeners = new HashSet<>();
    private boolean isRunning = false;

    public TimeController(int period)
    {
        this.period = period;

        timer = new Timer(true);
        JavaSplitter.HotkeyHandler.addHotkeyEventListener(this);
    }

    private class TimerTick extends TimerTask {

        @Override
        public void run() {
            long currentTime = System.nanoTime();
            checkIfFinished();
            dispatch(new TimeEvent(currentTime));
        }
    }

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
        switch (hotkey)
        {
            case START:
                if (ticker != null)
                    ticker.cancel();
                isRunning = true;
                Globals.START = current;
                ticker = new TimerTick();
                timer.schedule(ticker, 0, this.period);
                break;
            case RESET:
                System.out.println("RESET");
                if(ticker != null)
                    ticker.cancel();
                isRunning = true;
                Globals.START = current;
                dispatch(new TimeEvent(current));
                break;
            case SEGMENT:
                checkIfFinished();
                break;
        }
    }

    public void checkIfFinished()
    {
        if(JavaSplitter.RunController.hasFinished())
        {
            if (ticker != null)
                ticker.cancel();
            System.out.println("RUN FINISHED");
            Globals.END = System.nanoTime();
            isRunning = false;
        }
    }

    public boolean isRunning()
    {
        return isRunning;
    }

    public void addTimeEventListener(TimeEventListener listener)
    {
        listeners.add(listener);
    }
}
