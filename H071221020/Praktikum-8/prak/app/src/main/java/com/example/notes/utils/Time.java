package com.example.notes.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Time {

    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public static String getCurrentDateTime() {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault());
        return dateTimeFormat.format(new Date());
    }
}