package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.CustomException;
import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Log;
import com.group15A.DataModel.Patient;

import java.util.List;

public class LogLogic implements ILog {
    private DataAccess dataAccessLayer;

    public LogLogic() throws DatabaseException {
        this.dataAccessLayer = new DataAccess();
    }

    @Override
    public List<Log> getLogs(Integer patientID) throws CustomException {
        Patient patient = this.dataAccessLayer.getPatient(patientID);
        return this.dataAccessLayer.getLogs(patient);
    }
}
