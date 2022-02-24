package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Doctor;

import java.util.List;

/**
 * Contains backend functionality that relates to doctor data
 * in the database
 *
 * @author Milovan Gveric
 * @author Wenbo Wu
 */
public class DoctorLogic implements IDoctor {
    private DataAccess dataAccessLayer;

    /**
     * Constructor
     * @throws DatabaseException if issue connecting to the database
     */
    public DoctorLogic() throws DatabaseException {
        this.dataAccessLayer = new DataAccess();
    }

    /**
     * Retrieves all the doctors in the database
     *
     * @return a list of Doctor instances, each having data
     * @throws DatabaseException if issue in getting doctors from the database
     */
    @Override
    public List<Doctor> getDoctors() throws DatabaseException {
        return dataAccessLayer.getDoctors();
    }
}
