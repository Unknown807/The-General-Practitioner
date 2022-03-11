package com.group15A.BusinessLogic;

import com.group15A.DataModel.Booking;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;

/**
 * The interface for AddBookingLogic
 *
 * @author Milovan Gveric
 */
public interface IAddBooking {
    Booking createNewBooking(String date, String hour, String minute, Integer patientID) throws Exception;

    Doctor getPatientDoctor(Patient patient) throws Exception;

    Patient getPatient(Integer patientID) throws Exception;
}