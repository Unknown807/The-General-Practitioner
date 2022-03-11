package com.group15A.Utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class storing methods related to changing given data to a different format.
 *
 * @author Filip Fois
 */
public class DataModification
{
    /**
     * @return Capitalized version of given string (first letter uppercase and rest lowercase)
     */
    public static String capitalize(String string)
    {
        String newString = "";
        if(string != null && string.length() > 0) {
            newString += string.substring(0,1).toUpperCase();
            newString += string.substring(1).toLowerCase();
        }
        return newString;
    }

    /**
     * Receive a timestamp and return a shortened string version in format:
     * "dayName, dayNumber nonthName, yearNumber, hour:minute"
     *
     * @param timestamp The timestamp to be shortened
     * @return shortened timestamp string
     */
    public static String fullDate(Timestamp timestamp)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM, yyyy, HH:mm");
        return dateFormat.format(timestamp);
    }

    /**
     * Receive a timestamp and return a shortened string version in format:
     * "dayNumber/monthNumber/yearNumber, hour:minute"
     *
     * @param timestamp The timestamp to be shortened
     * @return shortened timestamp string
     */
    public static String shortDate(Timestamp timestamp)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd/MM/yyyy, HH:mm");
        return dateFormat.format(timestamp);
    }

}
