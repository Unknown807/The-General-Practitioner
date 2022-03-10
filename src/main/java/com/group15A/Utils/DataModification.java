package com.group15A.Utils;

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
}
