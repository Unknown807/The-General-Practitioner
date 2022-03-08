package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.DoctorNotFoundException;
import com.group15A.DataModel.Booking;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;

/**
 * The interface for AddBookingLogic
 *
 * @author Milovan Gveric
 */
public interface IAddBooking {
    public Booking createNewBooking(String date, String hour, String minute, Integer patientID) throws Exception;

    public Doctor getPatientDoctor(Patient patient) throws Exception;

    public Patient getPatient(Integer patientID) throws Exception;
}