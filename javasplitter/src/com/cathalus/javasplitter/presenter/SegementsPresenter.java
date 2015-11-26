package com.cathalus.javasplitter.presenter;

import com.cathalus.javasplitter.GUIApplication;
import com.cathalus.javasplitter.JavaSplitter;
import com.cathalus.javasplitter.events.HotkeyEvent;
import com.cathalus.javasplitter.events.HotkeyEventListener;
import com.cathalus.javasplitter.model.Segment;
import com.cathalus.javasplitter.view.Display;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Raymond on 25.11.2015.
 */
public class SegementsPresenter extends Presenter implements HotkeyEventListener {

    private GUIApplication app;
    private Pane parent;
    private SegmentsDisplay segmentsDisplay;
    private Segment currentSegment = null;
    private ArrayList<Label> labels = new ArrayList();

    public interface SegmentsDisplay extends Display {
        public Pane getSegmentsBox();
        public void setCurrentSplit(int i);
    }

    public SegementsPresenter(GUIApplication app, ArrayList<Display> displays, Pane parent)
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
        if(JavaSplitter.TimeController.isRunning()) {
            switch (e.getHotkey()) {
                case SEGMENT:
                    JavaSplitter.RunController.nextSplit();
                    markCurrentSplit(JavaSplitter.RunController.getCurrentSplitIndex());
                    break;
                case RESET:
                case START:
                    initializeSplits();
                    break;
            }
        }
    }

    private void initializeSplits() {
        LinkedList<Segment> segments = JavaSplitter.RunController.getSplits();
        Pane splitBox = segmentsDisplay.getSegmentsBox();

        javafx.application.Platform.runLater(() -> {
            // Clear and refill labels list
            labels.clear();
            for(Segment s : segments)
            {
                labels.add(new Label(s.getName()+" - "+s.getMiliseconds()));
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
            markCurrentSplit(JavaSplitter.RunController.getCurrentSplitIndex());

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
