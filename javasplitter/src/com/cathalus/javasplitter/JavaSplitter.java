package com.cathalus.javasplitter;

import com.cathalus.javasplitter.events.HotkeyEventHandler;
import com.cathalus.javasplitter.model.Run;
import com.cathalus.javasplitter.model.Split;
import com.cathalus.javasplitter.presenter.SplitsPresenter;
import com.cathalus.javasplitter.presenter.TimerPresenter;
import com.cathalus.javasplitter.util.Globals;
import com.cathalus.javasplitter.view.Display;
import com.cathalus.javasplitter.view.SplitsView;
import com.cathalus.javasplitter.view.TimerView;
import com.tulskiy.keymaster.common.Provider;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Cathalus on 24/11/2015.
 */
public class JavaSplitter extends GUIApplication {

    private final ArrayList<KeyStroke> strokes = new ArrayList<>();
    private ArrayList<Display> displays = new ArrayList<Display>();

    private Provider keyProvider;
    private Object lock = new Object();

    public static HotkeyEventHandler HotkeyHandler;
    public static TimeController TimeController;

    public JavaSplitter()
    {
        HotkeyHandler = new HotkeyEventHandler();
        TimeController = new TimeController(100);

        loadRun();
        setupProvider();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;
        // Exit program on window close
        this.window.setOnCloseRequest(event -> System.exit(0));

        Pane windowLayout = new BorderPane();

        presenters.put("TimerPresenter", new TimerPresenter(this, null, windowLayout));
        presenters.put("SplitsPresenter", new SplitsPresenter(this,null,windowLayout));

        views.put("TimerView", new TimerView());
        views.put("SplitsView", new SplitsView());

        displays.add(views.get("TimerView"));
        displays.add(views.get("SplitsView"));

        presenters.get("TimerPresenter").setDisplays(displays);
        presenters.get("TimerPresenter").start();
        presenters.get("SplitsPresenter").setDisplays(displays);
        presenters.get("SplitsPresenter").start();

        this.window.setScene(new Scene(windowLayout,300,300));
        this.window.show();
    }

    /**
     * Sets up the hotkey provider
     */
    private void setupProvider()
    {
        keyProvider = Provider.getCurrentProvider(false);
        // Register all defined strokes
        for(final KeyStroke stroke : Globals.HOTKEYS.values().stream().collect(Collectors.toList()))
        {
            keyProvider.register(stroke, hotKey -> {
                assert (SwingUtilities.isEventDispatchThread()==false);
                assert(stroke == hotKey.keyStroke);
                HotkeyHandler.handleInput(stroke);
                strokes.remove(stroke);
            });
        }
    }

    private void loadRun()
    {
        Globals.CURRENT_RUN = new Run("TestGame");
        Globals.CURRENT_RUN.addSplit(new Split("First",10000));
        Globals.CURRENT_RUN.addSplit(new Split("Second",15000));
        Globals.CURRENT_RUN.addSplit(new Split("First",10000));
        Globals.CURRENT_RUN.addSplit(new Split("Second",15000));
        Globals.CURRENT_RUN.addSplit(new Split("First",10000));
        Globals.CURRENT_RUN.addSplit(new Split("Second",15000));
        Globals.CURRENT_RUN.addSplit(new Split("First",10000));
        Globals.CURRENT_RUN.addSplit(new Split("Second",15000));
        Globals.CURRENT_RUN.addSplit(new Split("First",10000));
        Globals.CURRENT_RUN.addSplit(new Split("Second",15000));
        Globals.CURRENT_RUN.addSplit(new Split("First",10000));
        Globals.CURRENT_RUN.addSplit(new Split("Second",15000));
    }
}
