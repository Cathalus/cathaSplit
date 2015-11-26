package com.cathalus.javasplitter.events;

import com.cathalus.javasplitter.model.Hotkey;

/**
 * Created by Cathalus on 24/11/2015.
 */

/**
 * Event that gets dispatched when a registered hotkey is pressed
 */
public class HotkeyEvent {

    private Hotkey hotkey;

    public HotkeyEvent(Hotkey hotkey)
    {
        this.hotkey = hotkey;
    }

    /**
     * Returns the pressed hotkey
     * @return Pressed hotkey
     */
    public Hotkey getHotkey() {
        return hotkey;
    }
}
