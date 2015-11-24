package com.cathalus.javasplitter;

import com.cathalus.javasplitter.presenter.Presenter;
import com.cathalus.javasplitter.view.Display;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 * Created by Cathalus on 18/10/2015.
 */
public abstract class GUIApplication extends Application {

    protected Stage window;
    protected HashMap<String, Display> views = new HashMap<String,Display>();
    protected HashMap<String, Presenter> presenters = new HashMap<String, Presenter>();


    public Presenter getPresenterByName(String name) { return presenters.get(name); }
    public Display getViewByName(String name)
    {
        return views.get(name);
    }
    public Stage getWindow()
    {
        return window;
    }

}