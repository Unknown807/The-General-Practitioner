package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.DoctorNotFoundException;
import com.group15A.DataModel.Booking;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;

import java.util.Date;
import java.sql.Timestamp;

/**
 * The interface for AddBookingLogic
 *
 * @author Milovan Gveric
 */
public interface IAddBooking {
    public Booking createNewBooking(Date bookingDate, Timestamp bookingTime, Patient patient) throws Exception;

    public Doctor getPatientDoctor(Patient patient) throws DatabaseException, DoctorNotFoundException;
}