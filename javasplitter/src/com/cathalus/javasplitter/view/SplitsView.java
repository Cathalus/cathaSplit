package com.cathalus.javasplitter.view;

import com.cathalus.javasplitter.presenter.SplitsPresenter;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Created by Raymond on 25.11.2015.
 */
public class SplitsView implements SplitsPresenter.SplitsDisplay {

    private VBox box = new VBox();
    private VBox splits = new VBox();
    private Label test;

    public SplitsView()
    {
        box.getChildren().add(splits);
    }

    @Override
    public Pane getPane() {
        return box;
    }

    @Override
    public Pane getSplitBox() {
        return splits;
    }

    @Override
    public void setCurrentSplit(int i) {

    }
}
