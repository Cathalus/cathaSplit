package com.cathalus.javasplitter.events;

import com.cathalus.javasplitter.model.Hotkey;

/**
 * Created by Cathalus on 24/11/2015.
 */
public class HotkeyEvent {

    private Hotkey hotkey;

    public HotkeyEvent(Hotkey hotkey)
    {
        this.hotkey = hotkey;
    }

    public Hotkey getHotkey() {
        return hotkey;
    }
}
