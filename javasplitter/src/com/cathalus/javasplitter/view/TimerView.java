package com.cathalus.javasplitter.view;

import com.cathalus.javasplitter.presenter.TimerPresenter;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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
    private String lastStyle = "";

    public TimerView()
    {
        time = new Label("00:00");
        time.setAlignment(Pos.BOTTOM_RIGHT);
        time.getStyleClass().add("timer");
        box = new VBox();
        box.setAlignment(Pos.CENTER_RIGHT);
        box.getStyleClass().add("timer-box-green");
        box.getChildren().add(time);
    }

    @Override
    public Label getLabelTime() {
        return time;
    }

    @Override
    public void setTimerBoxStyle(String style) {
        if(style != lastStyle) {
            box.getStyleClass().remove("timer-box-red");
            box.getStyleClass().remove("timer-box-green");
            box.getStyleClass().remove("timer-box-neutral");
            box.getStyleClass().add(style);
            lastStyle = style;
        }
    }

    @Override
    public Pane getPane() {
        return box;
    }

}
