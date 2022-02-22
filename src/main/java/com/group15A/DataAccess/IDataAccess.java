package com.group15A.DataAccess;

import com.group15A.DataModel.*;

import java.util.List;

/**
 * An interface to the Data Access Layer
 *
 * @author Andrei
 */
public interface IDataAccess
{
    Patient getPatient(String email, String password) throws Exception;

    Patient getPatient(int patientID) throws Exception;

    Patient registerPatient(Patient patient, Doctor doctor) throws Exception;

    Patient updatePatient(Patient patient) throws Exception;

    Patient changeDoctor(Patient patient, Doctor doctor) throws Exception;

    List<Doctor> getDoctors() throws Exception;

    Doctor getDoctor(Patient patient) throws Exception;

    Doctor getDoctor(int doctorID) throws Exception;

    List<Certification> getCertifications(Doctor doctor) throws Exception;
}
