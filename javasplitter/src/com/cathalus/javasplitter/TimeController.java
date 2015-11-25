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
    private boolean toggle = false;

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
                Globals.IS_RUNNING = true;
                if (ticker != null)
                    ticker.cancel();
                Globals.START = current;
                ticker = new TimerTick();
                timer.schedule(ticker, 0, this.period);
                break;
            case RESET:
                Globals.IS_RUNNING = false;
                System.out.println("RESET");
                if(ticker != null)
                    ticker.cancel();
                Globals.START = current;
                dispatch(new TimeEvent(current));
                break;
            case SPLIT:
                if(Globals.CURRENT_SPLIT_ID == Globals.CURRENT_RUN.getSplits().size()-1)
                {
                    Globals.END = System.nanoTime();
                    if (ticker != null)
                        ticker.cancel();
                }
                break;
        }
    }

    public void addTimeEventListener(TimeEventListener listener)
    {
        listeners.add(listener);
    }
}
