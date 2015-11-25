package com.cathalus.javasplitter.util;

/**
 * Created by Raymond on 25.11.2015.
 */
public class TimerText {

    public static String getNumberString(int num)
    {
        if(num < 10)
        {
            return "0"+num;
        }
        return Integer.toString(num);
    }

}
