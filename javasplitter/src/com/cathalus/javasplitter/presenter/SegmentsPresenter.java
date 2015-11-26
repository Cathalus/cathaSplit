package com.cathalus.javasplitter.presenter;

import com.cathalus.javasplitter.GUIApplication;
import com.cathalus.javasplitter.JavaSplitter;
import com.cathalus.javasplitter.events.HotkeyEvent;
import com.cathalus.javasplitter.events.HotkeyEventListener;
import com.cathalus.javasplitter.model.Segment;
import com.cathalus.javasplitter.util.Globals;
import com.cathalus.javasplitter.util.TimerText;
import com.cathalus.javasplitter.view.Display;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Raymond on 25.11.2015.
 */
public class SegmentsPresenter extends Presenter implements HotkeyEventListener {

    private GUIApplication app;
    private Pane parent;
    private SegmentsDisplay segmentsDisplay;
    private Segment currentSegment = null;
    private ArrayList<Label> labels = new ArrayList();

    public interface SegmentsDisplay extends Display {
        public Pane getSegmentsBox();
        public void setCurrentSplit(int i);
    }

    public SegmentsPresenter(GUIApplication app, ArrayList<Display> displays, Pane parent)
    {
        this.app = app;
        this.displays = displays;
        this.parent = parent;
    }

    @Override
    public void start() {

        for(Display d : displays)
        {
            if(d instanceof SegmentsDisplay)
            {
                segmentsDisplay = (SegmentsDisplay) d;
            }
        }
        bind();
        JavaSplitter.HotkeyHandler.addHotkeyEventListener(this);
        initializeSplits();
    }

    @Override
    public void bind() {
        if(parent instanceof BorderPane)
        {
            ((BorderPane) parent).setCenter(segmentsDisplay.getPane());
        }
    }

    @Override
    public void handleHotkeyEvent(HotkeyEvent e) {
        switch (e.getHotkey()) {
            case SPLIT:
                if(JavaSplitter.TimeController.isRunning()) {
                    int currentSplit = (int) (JavaSplitter.TimeController.getLastSplit() / 1000000);
                    JavaSplitter.RunController.split(currentSplit);
                    markCurrentSplit(JavaSplitter.RunController.getCurrentSegmentIndex());
                }
                break;
            case START:
                break;
            case RESET:
                JavaSplitter.RunController.reset();
                break;
        }
    initializeSplits();
    }

    @Override
    public int getPriority() {
        return Globals.HK_PRIORITY_SEGMENTSPRESENTER;
    }

    private void initializeSplits() {
        LinkedList<Segment> segments = JavaSplitter.RunController.getSegments();
        Pane splitBox = segmentsDisplay.getSegmentsBox();

        javafx.application.Platform.runLater(() -> {
            // Clear and refill labels list
            labels.clear();
            for(Segment s : segments)
            {
                labels.add(new Label(s.getName()+" - "+ TimerText.toReadableTime(s.getBestTime(),true)+" | "+TimerText.toReadableTime(s.getCurrentTime(),true) + " | " + TimerText.toReadableTime(s.getDifference(),false)));
            }

            // clear list of segments
            splitBox.getChildren().clear();

            int i = 0;
            // Add split labels to splitbox
            for(Segment s: segments)
            {
                splitBox.getChildren().add(labels.get(i));
                i++;
            }

            // Set opacity for the current split
            markCurrentSplit(JavaSplitter.RunController.getCurrentSegmentIndex());

        });
    }

    private void markCurrentSplit(int index)
    {
        if(labels != null)
        {
            for(Label l : labels)
            {
                l.setOpacity(1.0f);
            }
            labels.get(index).setOpacity(0.5f);
        }
    }
}
