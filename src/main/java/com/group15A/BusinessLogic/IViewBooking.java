package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.DoctorNotFoundException;
import com.group15A.DataModel.Booking;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;

import java.util.List;

/**
 * The interface for ViewBookingLogic
 *
 * @author Milovan Gverics
 */
public interface IViewBooking {
    public List<Booking> getBookings(Integer patientID) throws Exception;
}
