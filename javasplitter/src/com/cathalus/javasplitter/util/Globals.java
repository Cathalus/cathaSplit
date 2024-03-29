package com.cathalus.javasplitter.util;

import com.cathalus.javasplitter.model.Hotkey;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by Cathalus on 24/11/2015.
 */
public class Globals {

    /**
     * Maps all <code>Hotkey</code>s to <code>Keystroke</code>s
     */
    public static HashMap<Hotkey, KeyStroke> HOTKEYS = new HashMap<Hotkey,KeyStroke>(){{
        put(Hotkey.START, KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        put(Hotkey.SPLIT, KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
        put(Hotkey.RESET, KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
        put(Hotkey.NEXT, KeyStroke.getKeyStroke(KeyEvent.VK_F9,0));
    }};

    /**
     * Start time of the run
     */
    public static long START;
    /**
     * End time of the run
     */
    public static long END;

    public static final int HK_PRIORITY_TIMECONTROLLER = 9;
    public static final int HK_PRIORITY_SEGMENTSPRESENTER = 8;
}
