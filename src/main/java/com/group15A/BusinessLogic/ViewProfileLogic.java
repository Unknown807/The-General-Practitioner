package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.CustomException;
import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;

/**
 * @author Milovan Gveric
 */
public class ViewProfileLogic implements IViewProfile {
    private DataAccess dataAccess;

    public ViewProfileLogic() throws DatabaseException {
        this.dataAccess = new DataAccess();
    }

    @Override
    public void updatePatientDoctor(Integer patientID, Doctor newDoctor) throws CustomException {
        Patient patient = this.dataAccess.getPatient(patientID);
        Doctor oldDoctor = this.dataAccess.getDoctor(patient);
        this.dataAccess.changeDoctor(patient, newDoctor);
        this.dataAccess.createNotification(patient, "Doctor Changed", "You changed your doctor from "+oldDoctor.getFullName()+" to "+newDoctor.getFullName());
    }
}
