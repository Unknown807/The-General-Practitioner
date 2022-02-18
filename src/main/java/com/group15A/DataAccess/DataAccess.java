package com.group15A.DataAccess;

import com.group15A.CustomExceptions.PatientNotFoundException;
import com.group15A.DataModel.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to connect to the database and query the database using stored procedures.
 *
 * @author Andrei Constantin
 */
public class DataAccess implements IDataAccess
{
    private Connection connection;

    public DataAccess() throws Exception
    {
        setupConnection();
    }

    /**
     * Get the patient with the given email and password
     * @param email The patient's email
     * @param password The patient's password
     * @return The patient
     * @throws PatientNotFoundException if the user was not found
     * @throws Exception if there was a problem querying the database
     */
    @Override
    public Patient getPatient(String email, String password) throws PatientNotFoundException, Exception
    {
        String query = "CALL find_patient(?, ?);";
        CallableStatement statement = connection.prepareCall(query);
        statement.setString(1, email);
        statement.setString(2, password);

        Patient patient = null;
        ResultSet result = statement.executeQuery();
        try {
            result.next();
            patient = new Patient(
                    result.getInt("id_patient"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("first_name"),
                    result.getString("middle_name"),
                    result.getString("last_name"),
                    result.getDate("date_of_birth"),
                    result.getString("gender"),
                    result.getString("telephone_number")
            );
        } catch(Exception ex)
        {
            throw new PatientNotFoundException();
        }

        return patient;
    }

    /**
     * Registers a new patient
     * @param patient The new patient
     * @return The corresponding patient from the database
     * @throws Exception if there was a problem querying the database
     */
    @Override
    public Patient registerPatient(Patient patient) throws Exception
    {
        String query = "CALL insert_patient(?, ?, ?, ?, ?, ?, ?, ?);";
        CallableStatement statement = connection.prepareCall(query);
        statement.setString(1, patient.getEmail());
        statement.setString(2, patient.getPassHash());
        statement.setString(3, patient.getFirstName());
        statement.setString(4, patient.getMiddleName());
        statement.setString(5, patient.getLastName());
        statement.setDate(6, new Date(patient.getDob().getTime()));
        statement.setString(7, patient.getGender());
        statement.setString(8, patient.getPhoneNo());

        statement.executeQuery();

        return getPatient(patient.getEmail(), patient.getPassHash());
    }

    /**
     * Update the given patient with the new information provided in the object
     * @param patient The modified patient
     * @return The corresponding patient from the database
     * @throws Exception if there was a problem querying the database
     */
    @Override
    public Patient updatePatient(Patient patient) throws Exception
    {
        String query = "CALL update_patient(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        CallableStatement statement = connection.prepareCall(query);
        statement.setInt(1, patient.getPatientID());
        statement.setString(2, patient.getEmail());
        statement.setString(3, patient.getPassHash());
        statement.setString(4, patient.getFirstName());
        statement.setString(5, patient.getMiddleName());
        statement.setString(6, patient.getLastName());
        statement.setDate(7, new Date(patient.getDob().getTime()));
        statement.setString(8, patient.getGender());
        statement.setString(9, patient.getPhoneNo());

        statement.executeQuery();

        return getPatient(patient.getEmail(), patient.getPassHash());
    }

    /**
     * Get the full list of doctors from the database
     * @return The list of doctors
     * @throws Exception if there was a problem querying the database
     */
    public List<Doctor> getDoctors() throws Exception
    {
        String query = "CALL get_doctors();";
        CallableStatement statement = connection.prepareCall(query);
        //statement.setString(1, "smth");
        ResultSet result = statement.executeQuery();
        var doctors = new ArrayList<Doctor>();
        while(result.next())
        {
            doctors.add(new Doctor(
                    result.getInt("id_doctor"),
                    result.getString("email"),
                    result.getString("first_name"),
                    result.getString("middle_name"),
                    result.getString("last_name"),
                    result.getDate("date_of_birth"),
                    result.getString("gender"),
                    result.getString("telephone_number")
            ));
        }

        return doctors;
    }


    @Override
    public List<Certification> getCertifications(Doctor doctor) throws Exception
    {
        return null;
    }

    /**
     * Setup the connection to the database
     * @throws Exception if the connection could not be established
     */
    private void setupConnection() throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/TheGeneralPractitioner?user=Andrei&password=lZWzuM3fuz5okeUSwE");
    }


}
