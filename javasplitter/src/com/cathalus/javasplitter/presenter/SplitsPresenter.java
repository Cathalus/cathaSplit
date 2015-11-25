package com.cathalus.javasplitter.presenter;

import com.cathalus.javasplitter.GUIApplication;
import com.cathalus.javasplitter.JavaSplitter;
import com.cathalus.javasplitter.events.HotkeyEvent;
import com.cathalus.javasplitter.events.HotkeyEventListener;
import com.cathalus.javasplitter.model.Split;
import com.cathalus.javasplitter.util.Globals;
import com.cathalus.javasplitter.view.Display;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Raymond on 25.11.2015.
 */
public class SplitsPresenter extends Presenter implements HotkeyEventListener {

    private GUIApplication app;
    private Pane parent;
    private SplitsDisplay splitsDisplay;
    private Split currentSplit = null;
    private ArrayList<Label> labels = new ArrayList();

    public interface SplitsDisplay extends Display {
        public Pane getSplitBox();
        public void setCurrentSplit(int i);
    }

    public SplitsPresenter(GUIApplication app, ArrayList<Display> displays, Pane parent)
    {
        this.app = app;
        this.displays = displays;
        this.parent = parent;
    }

    @Override
    public void start() {

        for(Display d : displays)
        {
            if(d instanceof SplitsDisplay)
            {
                splitsDisplay = (SplitsDisplay) d;
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
            ((BorderPane) parent).setCenter(splitsDisplay.getPane());
        }
    }

    @Override
    public void handleHotkeyEvent(HotkeyEvent e) {
        switch (e.getHotkey())
        {
            case SPLIT:
                if(Globals.CURRENT_SPLIT_ID < Globals.CURRENT_RUN.getSplits().size()-1) {
                    if (Globals.CURRENT_SPLIT_ID >= 0)
                        labels.get(Globals.CURRENT_SPLIT_ID).setOpacity(1.0f);
                    Globals.CURRENT_SPLIT_ID++;
                    labels.get(Globals.CURRENT_SPLIT_ID).setOpacity(0.5f);
                    currentSplit = Globals.CURRENT_RUN.getSplits().get(Globals.CURRENT_SPLIT_ID);
                }
                break;
            case RESET:
            case START:
                initializeSplits();
                break;
        }
    }

    private void initializeSplits() {
        LinkedList<Split> splits = Globals.CURRENT_RUN.getSplits();
        Pane splitBox = splitsDisplay.getSplitBox();

        labels.clear();
        for(Split s : splits)
        {
            labels.add(new Label(s.getName()));
        }


        javafx.application.Platform.runLater(() -> {
            splitBox.getChildren().clear();

            int i = 0;
            for (Split s : splits) {
                splitBox.getChildren().add(labels.get(i));
                labels.get(i).setOpacity(1.0f);
                i++;
            }

            // Sets opacity for the current split
            Globals.CURRENT_SPLIT_ID = 0;
            labels.get(0).setOpacity(0.5f);
            currentSplit = splits.get(0);
        });
    }
}
