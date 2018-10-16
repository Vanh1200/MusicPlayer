package com.vanh1200.musicapp.utils;

public class ConvertUtils {
    public static String longToTime(long duration){
        long m = (duration / 1000) / 60;
        long s = (duration / 1000) % 60;
        String m_ = String.valueOf(m);
        String s_ = String.valueOf(s);
        if(m_.length() < 2) m_ = "0" + m_;
        if(s_.length() < 2) s_ = "0" + s_;
        return m_ + ":" + s_;
    }
}
