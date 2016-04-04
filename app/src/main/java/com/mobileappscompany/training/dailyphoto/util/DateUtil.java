package com.mobileappscompany.training.dailyphoto.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author mpamplona
 * Created on 4/3/2016
 * <p/>
 * Util class for managing Date
 */
public class DateUtil {

    private static SimpleDateFormat format = new SimpleDateFormat("M/dd/yyyy");

    public static String toString(Date date) {
        return format.format(date);
    }

    public static Date fromString(String stringDate) {
        Date date = null;
        try {
            date = format.parse(stringDate);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return date;
    }
}
