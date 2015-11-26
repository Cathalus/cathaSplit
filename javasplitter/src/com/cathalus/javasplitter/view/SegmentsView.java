package com.cathalus.javasplitter.view;

import com.cathalus.javasplitter.presenter.SegementsPresenter;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Created by Raymond on 25.11.2015.
 */
public class SegmentsView implements SegementsPresenter.SegmentsDisplay {

    private VBox box = new VBox();
    private VBox splits = new VBox();
    private Label test;

    public SegmentsView()
    {
        box.getChildren().add(splits);
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
