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

    Patient registerPatient(Patient patient) throws Exception;

    Patient updatePatient(Patient patient) throws Exception;

    List<Doctor> getDoctors() throws Exception;

    List<Certification> getCertifications(Doctor doctor) throws Exception;
}
