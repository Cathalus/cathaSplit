package com.cathalus.javasplitter.view;

import com.cathalus.javasplitter.presenter.SegmentsPresenter;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Created by Raymond on 25.11.2015.
 */

/**
 * Displays the list of <code>Segment</code>s in a <code>Run</code>
 */
public class SegmentsView implements SegmentsPresenter.SegmentsDisplay {

    private VBox box = new VBox();
    private VBox splits = new VBox();
    private Label test;

    public SegmentsView()
    {
        box.getChildren().add(splits);
        splits.getStyleClass().add("segment-box");
    }

    @Override
    public Pane getPane() {
        return box;
    }

    @Override
    public Pane getSegmentsBox() {
        return splits;
    }

    @Override
    public void setCurrentSplit(int i) {

    }
}
