package com.group15A.DataAccess;

import com.group15A.CustomExceptions.CustomException;
import com.group15A.DataModel.*;

import java.util.List;

/**
 * An interface to the Data Access Layer
 *
 * @author Andrei Constantin
 */
public interface IDataAccess
{
    Patient getPatient(String email, String password) throws CustomException;

    Patient getPatient(int patientID) throws CustomException;

    Patient registerPatient(Patient patient, Doctor doctor) throws CustomException;

    Patient updatePatient(Patient patient) throws CustomException;

    Patient changeDoctor(Patient patient, Doctor doctor) throws CustomException;

    List<Doctor> getDoctors() throws CustomException;

    Doctor getDoctor(Patient patient) throws CustomException;

    Doctor getDoctor(int doctorID) throws CustomException;

    List<Certification> getCertifications(Doctor doctor) throws CustomException;

    Booking getBooking(int bookingID) throws CustomException;

    List<Booking> getBookings() throws CustomException;

    List<Booking> getBookings(Doctor doctor) throws CustomException;
}
