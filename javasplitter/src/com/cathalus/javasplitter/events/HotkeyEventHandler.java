package com.cathalus.javasplitter.events;

import com.cathalus.javasplitter.model.Hotkey;
import com.cathalus.javasplitter.util.Globals;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Cathalus on 24/11/2015.
 */
public class HotkeyEventHandler {

    private ArrayList<HotkeyEventListener> listeners = new ArrayList<>();

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

    private void dispatch(HotkeyEvent e)
    {
        for(HotkeyEventListener listener : listeners)
        {
            listener.handleHotkeyEvent(e);
        }
    }

    public void addHotkeyEventListener(HotkeyEventListener listener)
    {
        if(!listeners.contains(listener))
            listeners.add(listener);
    }

}
