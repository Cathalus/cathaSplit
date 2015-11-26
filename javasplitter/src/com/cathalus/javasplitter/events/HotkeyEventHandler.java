package com.cathalus.javasplitter.events;

import com.cathalus.javasplitter.model.Hotkey;
import com.cathalus.javasplitter.util.Globals;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by Cathalus on 24/11/2015.
 */

/**
 * Handles Hotkey Events
 */
public class HotkeyEventHandler {

    /**
     * Contains all registered listeners
     */
    private HashSet<HotkeyEventListener> listeners = new HashSet<>();

    /**
     * Matches the <code>KeyStroke</code> to a hotkey and calls the dispatch method
     * @param stroke Pressed key
     */
    public void handleInput(KeyStroke stroke)
    {
        Hotkey key = Hotkey.DEFAULT;
        for(Map.Entry<Hotkey, KeyStroke> entry : Globals.HOTKEYS.entrySet())
        {
            if(stroke.equals(entry.getValue()))
            {
                key = entry.getKey();
                break;
            }
        }
        if(key != Hotkey.DEFAULT)
            dispatch(new HotkeyEvent(key));
    }

    /**
     * Dispatches the key event to all listeners
     * @param e Specifies which <code>HotkeyEvent</code> to dispatch
     */
    private void dispatch(HotkeyEvent e)
    {
        for(HotkeyEventListener listener : listeners)
        {
            listener.handleHotkeyEvent(e);
        }
    }

    /**
     * Adds a <code>HotkeyListener</code> to the set of listeners
     * @param listener
     */
    public void addHotkeyEventListener(HotkeyEventListener listener)
    {
        listeners.add(listener);
    }

}
