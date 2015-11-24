package com.cathalus.javasplitter.presenter;

import com.cathalus.javasplitter.GUIApplication;
import com.cathalus.javasplitter.JavaSplitter;
import com.cathalus.javasplitter.events.HotkeyEvent;
import com.cathalus.javasplitter.events.HotkeyEventListener;
import com.cathalus.javasplitter.model.Hotkey;
import com.cathalus.javasplitter.util.Globals;
import com.cathalus.javasplitter.view.Display;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Cathalus on 24/11/2015.
 */
public class TimerPresenter extends Presenter implements HotkeyEventListener, ActionListener {

    private GUIApplication app;
    private Pane parent;
    private TimerDisplay timerDisplay;
    private Timer timer;
    private TimerTick ticker;


    private class TimerTick extends TimerTask{

        @Override
        public void run() {
            updateTimer();
        }
    }

    public interface TimerDisplay extends Display {
        abstract Label getLabelTime();
    }

    public TimerPresenter(GUIApplication app, ArrayList<Display> displays, Pane parent)
    {
        this.app = app;
        this.displays = displays;
        this.parent = parent;
        JavaSplitter.HotkeyHandler.addHotkeyEventListener(this);
        timer = new Timer(true);
    }

    @Override
    public void start() {
        for(Display d : displays)
        {
            if(d instanceof TimerDisplay)
            {
                timerDisplay = (TimerDisplay) d;
            }
        }

        bind();
    }

    @Override
    public void bind() {
        if(parent instanceof BorderPane)
        {
            ((BorderPane) parent).setRight(timerDisplay.getPane());
        }
    }

    public void updateTimer()
    {
        long currentTime = System.nanoTime();
        long elapsedTime = currentTime-Globals.START;
        int seconds = (int) (elapsedTime/1000000000.0);
        int sec = seconds % 60;
        int min = seconds/60;
        int hr = min / 60;
        javafx.application.Platform.runLater(() -> {
            timerDisplay.getLabelTime().setText(hr + ":" + min + ":" + sec);
        });
    }

    @Override
    public void handleHotkeyEvent(HotkeyEvent e) {

        Hotkey hotkey = e.getHotkey();
        switch (hotkey)
        {
            case START:
                System.out.println("START");
                Globals.START = System.nanoTime();
                if(ticker != null)
                    ticker.cancel();
                ticker = new TimerTick();
                timer.schedule(ticker,0,100);
                break;
            case SPLIT:
                System.out.println("SPLIT");
                break;
            case RESET:
                System.out.println("RESET");
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
