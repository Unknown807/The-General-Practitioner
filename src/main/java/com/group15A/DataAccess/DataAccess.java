package com.group15A.DataAccess;

import com.group15A.DataModel.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Used to connect to the database and query the database using stored procedures.
 *
 * @author Andrei Constantin
 */
public class DataAccess
{
    private Connection connection;

    public DataAccess()
    {
        try {
            setupConnection();
        }catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    public List<Doctor> getDoctors()
    {
        try{
            String query = "CALL get_doctors();";
            CallableStatement statement = connection.prepareCall(query);
            //statement.setString(1, "smth");
            ResultSet result = statement.executeQuery();
            while(result.next())
            {
                System.out.println(result.getString("first_name"));
            }

            return new ArrayList<Doctor>();

        }catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }

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
