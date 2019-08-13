package com.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateUtil {

    public static String minFormat(Timestamp timestamp){
        SimpleDateFormat smft = new SimpleDateFormat("YYYY-MM-dd HH:mm");
        return smft.format(timestamp);
    }

    public static String hourFormat(Timestamp timestamp){
        SimpleDateFormat smft = new SimpleDateFormat("YYYY-MM-dd HH");
        return smft.format(timestamp);
    }

    public static String dayFormat(Timestamp timestamp){
        SimpleDateFormat smft = new SimpleDateFormat("YYYY-MM-dd ");
        return smft.format(timestamp);
    }

}
