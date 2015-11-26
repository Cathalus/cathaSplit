package com.cathalus.javasplitter.util;

import com.cathalus.javasplitter.model.Hotkey;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by Cathalus on 24/11/2015.
 */
public class Globals {

    public static HashMap<Hotkey, KeyStroke> HOTKEYS = new HashMap<Hotkey,KeyStroke>(){{
        put(Hotkey.START, KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        put(Hotkey.SEGMENT, KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
        put(Hotkey.RESET, KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
    }};

    public static long START = System.nanoTime();
    public static long END;
}
