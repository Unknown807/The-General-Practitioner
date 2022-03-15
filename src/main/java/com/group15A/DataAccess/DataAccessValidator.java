package com.group15A.DataAccess;

import com.group15A.DataModel.Patient;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class DataAccessValidator
{
    /**
     * Check if the given patient is valid or not
     * @param patient The patient to be checked
     * @return true if the patient is valid, false otherwise
     */
    protected static boolean validatePatient(Patient patient)
    {
        if(patient.getPatientID()<0)
            return false;
        if(isNullOrEmpty(patient.getEmail()))
            return false;
        if(patient.getPassHash()==null)
            return false;
        if(isNullOrEmpty(patient.getFirstName()))
            return false;
        if(isNullOrEmpty(patient.getLastName()))
            return false;
        if(patient.getDob()==null || dateAfterToday(patient.getDob()))
            return false;

        return true;
    }


    /**
     * Check if the given date is after today
     * @param date The date
     * @return true if the date is after today, false otherwise
     */
    private static boolean dateAfterToday(Date date)
    {
        return date.after(new Date());
    }

    /**
     * Check if the given date is after today
     * @param date The date
     * @return true if the date is after today, false otherwise
     */
    private static boolean dateAfterToday(Timestamp date)
    {
        return date.after(new Timestamp(System.currentTimeMillis()));
    }

    /**
     * Check if the given string is null or empty
     * @param string The string to verify
     * @return true if the string is null or empty, false otherwise
     */
    private static boolean isNullOrEmpty(String string)
    {
        if(string==null)
            return true;
        if(string.isBlank())
            return true;
        if(string.isBlank())
            return true;

        return false;
    }
}
