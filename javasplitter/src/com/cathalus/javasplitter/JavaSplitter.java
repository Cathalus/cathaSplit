package com.cathalus.javasplitter;

import com.cathalus.javasplitter.events.HotkeyEvent;
import com.cathalus.javasplitter.events.HotkeyEventHandler;
import com.cathalus.javasplitter.events.HotkeyEventListener;
import com.cathalus.javasplitter.files.RunParser;
import com.cathalus.javasplitter.model.Hotkey;
import com.cathalus.javasplitter.presenter.SegmentsPresenter;
import com.cathalus.javasplitter.presenter.TimerPresenter;
import com.cathalus.javasplitter.util.Globals;
import com.cathalus.javasplitter.view.Display;
import com.cathalus.javasplitter.view.SegmentsView;
import com.cathalus.javasplitter.view.TimerView;
import com.tulskiy.keymaster.common.Provider;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Cathalus on 24/11/2015.
 */
public class JavaSplitter extends GUIApplication implements HotkeyEventListener {

    private final ArrayList<KeyStroke> strokes = new ArrayList<>();
    private ArrayList<Display> displays = new ArrayList<Display>();

    private Provider keyProvider;
    private Object lock = new Object();

    public static HotkeyEventHandler HotkeyHandler;
    public static TimeController TimeController;
    public static RunController RunController;

    private Scene scene;

    public JavaSplitter()
    {
        HotkeyHandler = new HotkeyEventHandler();
        TimeController = new TimeController(10);

        HotkeyHandler.addHotkeyEventListener(this);

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
        presenters.put("SegmentsPresenter", new SegmentsPresenter(this,null,windowLayout));

        views.put("TimerView", new TimerView());
        views.put("SegmentsView", new SegmentsView());

        displays.add(views.get("TimerView"));
        displays.add(views.get("SegmentsView"));

        presenters.get("TimerPresenter").setDisplays(displays);
        presenters.get("TimerPresenter").start();
        presenters.get("SegmentsPresenter").setDisplays(displays);
        presenters.get("SegmentsPresenter").start();

        scene = new Scene(windowLayout,300,300);
        scene.getStylesheets().add("basic.css");
        this.window.setScene(scene);
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

    public void reloadStyle()
    {
        if(scene.getStylesheets().contains("basic.css")) {
            scene.getStylesheets().remove("basic.css");
        }else {
            scene.getStylesheets().add("basic.css");
        }
    }

    private void loadRun()
    {
        try {
            RunParser parser = new RunParser(new FileInputStream("res/test.lss"));
            RunController = new RunController(parser.getRun());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        /*
        Run current = new Run("Test: THE GAME!");
        current.addSegment(new Segment("First",1500));
        current.addSegment(new Segment("Second",1500));
        current.addSegment(new Segment("Third",1500));
        current.addSegment(new Segment("Fourth",1500));
        RunController = new RunController(current);*/
    }

    @Override
    public void handleHotkeyEvent(HotkeyEvent e) {
        if(e.getHotkey() == Hotkey.NEXT)
        {
            reloadStyle();
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
