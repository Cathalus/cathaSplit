package com.cathalus.javasplitter.util;

/**
 * Created by Raymond on 25.11.2015.
 */
public class TimerText {

    public static String getNumberString(int num)
    {
        num = Math.abs(num);
        if(num < 10 && num >= 0)
        {
            return "0"+num;
        }
        return Integer.toString(num);
    }

    public static String toReadableTime(int milliseconds, boolean msPrecision)
    {
        String time = "";
        if(milliseconds < 0)
            time += "-";
        int seconds = milliseconds/1000;
        int ms = (milliseconds / 10)%100;
        int sec = seconds % 60;
        int min = seconds/60;
        int hr = min / 60;
        if(hr > 0)
        {
            time += getNumberString(hr);
            time += ":";
        }
        if(min > 0)
        {
            time += getNumberString(min);
            time += ":";
        }
        time += getNumberString(sec);
        if(msPrecision)
            time += ":"+getNumberString(ms);

        return time;
    }

}
