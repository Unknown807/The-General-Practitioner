package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.PatientNotFoundException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Patient;
import com.group15A.Session;

public class PatientLogic
{
    DataAccess dataAccess;

    public PatientLogic() throws DatabaseException {
        dataAccess = new DataAccess();
    }

    public String getPatientFirstName(Session session)
    {
        String patientFirstName = null;
        try {
            patientFirstName = dataAccess.getPatient(session.getLoggedInPatientID()).getFirstName();
        } catch (PatientNotFoundException e) {
            e.printStackTrace();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return patientFirstName;
    }


}
