package com.cathalus.javasplitter.view;

import com.cathalus.javasplitter.presenter.TimerPresenter;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Created by Cathalus on 24/11/2015.
 */
public class TimerView implements TimerPresenter.TimerDisplay {

    private Label time;
    private VBox box;

    public TimerView()
    {
        time = new Label("TIME");
        time.setFont(Font.font("Verdana", 22));
        time.setAlignment(Pos.BOTTOM_RIGHT);
        box = new VBox();
        box.getChildren().add(time);
    }

    @Override
    public Label getLabelTime() {
        return time;
    }

    @Override
    public Pane getPane() {
        return box;
    }
}
