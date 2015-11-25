package com.cathalus.javasplitter.util;

import com.cathalus.javasplitter.model.Hotkey;
import com.cathalus.javasplitter.model.Run;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by Cathalus on 24/11/2015.
 */
public class Globals {

    public static HashMap<Hotkey, KeyStroke> HOTKEYS = new HashMap<Hotkey,KeyStroke>(){{
        put(Hotkey.START, KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        put(Hotkey.SPLIT, KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
        put(Hotkey.RESET, KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
    }};

    public static long START = System.nanoTime();
    public static long END;
    public static Run CURRENT_RUN;
    public static boolean IS_RUNNING = false;
    public static int CURRENT_SPLIT_ID = 0;
}
