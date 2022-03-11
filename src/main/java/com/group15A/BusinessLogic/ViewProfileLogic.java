package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.CustomException;
import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;

/**
 * Contains backend functionality for changing a patient's doctor
 *
 * @author Milovan Gveric
 */
public class ViewProfileLogic implements IViewProfile {
    private DataAccess dataAccessLayer;

    /**
     * Constructor for view profile logic
     * @throws DatabaseException if issues connecting to the database
     */
    public ViewProfileLogic() throws DatabaseException {
        this.dataAccessLayer = new DataAccess();
    }

    /**
     * Changes the patient's current doctor to a new doctor
     * @param patientID the patient
     * @param newDoctor the patient's new doctor
     * @throws CustomException if issue with finding the patient, doctor, updating the patient's
     * new doctor or when finally creating a notification
     */
    @Override
    public void updatePatientDoctor(Integer patientID, Doctor newDoctor) throws CustomException {
        Patient patient = this.dataAccessLayer.getPatient(patientID);
        Doctor oldDoctor = this.dataAccessLayer.getDoctor(patient);
        this.dataAccessLayer.changeDoctor(patient, newDoctor);
        this.dataAccessLayer.createNotification(patient, "Doctor Changed", "You changed your doctor from "+oldDoctor.getFullName()+" to "+newDoctor.getFullName());
    }
}
