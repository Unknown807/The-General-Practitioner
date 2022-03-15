package com.group15A.DataAccess;

import com.group15A.DataModel.Booking;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Notification;
import com.group15A.DataModel.Patient;

import javax.print.Doc;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Validator for the Data Access Layer
 *
 * @author Andrei Constantin
 */
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
     * Check if the given doctor is valid or not
     * @param doctor The doctor to be checked
     * @return true if the doctor is valid, false otherwise
     */
    protected static boolean validateDoctor(Doctor doctor)
    {
        if(doctor.getDoctorID()<0)
            return false;
        if(isNullOrEmpty(doctor.getEmail()))
            return false;
        if(isNullOrEmpty(doctor.getFirstName()))
            return false;
        if(isNullOrEmpty(doctor.getLastName()))
            return false;
        if(doctor.getDob()==null || dateAfterToday(doctor.getDob()))
            return false;

        return true;
    }

    /**
     * Check if the given booking is valid or not
     * @param booking The booking to be checked
     * @return true if the booking is valid, false otherwise
     */
    protected static boolean validateBooking(Booking booking)
    {
        if(booking.getBookingID()<0)
            return false;
        if(booking.getPatientID()<0)
            return false;
        if(booking.getDoctorID()<0)
            return false;
        if(booking.getBookingTime()==null)
            return false;
        return true;
    }

    /**
     * Check if the given notification is valid or not
     * @param notification The notification to be checked
     * @return true if the notification is valid, false otherwise
     */
    protected static boolean validateNotification(Notification notification)
    {
        if(notification.getNotifID()<0)
            return false;
        if(notification.getPatientID()<0)
            return false;
        if(isNullOrEmpty(notification.getMessage()))
            return false;
        if(isNullOrEmpty(notification.getHeader()))
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
    public static boolean isNullOrEmpty(String string)
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
