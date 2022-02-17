package com.group15A.DataAccess;

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

    @Override
    public Patient getPatient(String email, String password) throws Exception
    {
        return null;
    }

    @Override
    public Patient registerPatient(Patient patient) throws Exception
    {
        return null;
    }

    @Override
    public Patient updatePatient(Patient patient) throws Exception
    {
        return null;
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
