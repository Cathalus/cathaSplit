package com.cathalus.javasplitter.presenter;

import com.cathalus.javasplitter.GUIApplication;
import com.cathalus.javasplitter.JavaSplitter;
import com.cathalus.javasplitter.RunController;
import com.cathalus.javasplitter.events.TimeEvent;
import com.cathalus.javasplitter.events.TimeEventListener;
import com.cathalus.javasplitter.util.Globals;
import com.cathalus.javasplitter.util.TimerText;
import com.cathalus.javasplitter.view.Display;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * Created by Cathalus on 24/11/2015.
 */
public class TimerPresenter extends Presenter implements TimeEventListener {

    private GUIApplication app;
    private Pane parent;
    private TimerDisplay timerDisplay;

    public interface TimerDisplay extends Display {
        abstract Label getLabelTime();
        public void setTimerBoxStyle(String style);
    }

    public TimerPresenter(GUIApplication app, ArrayList<Display> displays, Pane parent)
    {
        this.app = app;
        this.displays = displays;
        this.parent = parent;
    }

    @Override
    public void start() {
        JavaSplitter.TimeController.addTimeEventListener(this);

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
            ((BorderPane) parent).setBottom(timerDisplay.getPane());
        }
    }

    public void updateTimer(long currentTime)
    {
        long elapsedTime = currentTime-Globals.START;
        int miliseconds = (int) (elapsedTime/1000000);
        javafx.application.Platform.runLater(() -> {
            timerDisplay.getLabelTime().setText(TimerText.toReadableTime(miliseconds,true));
        });

        if(miliseconds < JavaSplitter.RunController.getCurrent().getBestTime())
        {
            timerDisplay.setTimerBoxStyle("timer-box-green");
        }else{
            timerDisplay.setTimerBoxStyle("timer-box-red");
        }
        if(JavaSplitter.RunController.hasFinished())
        {
            timerDisplay.setTimerBoxStyle("timer-box-neutral");
        }
    }

    @Override
    public void onTimeEvent(TimeEvent e) {
        updateTimer(e.getCurrentTime());
    }

}
