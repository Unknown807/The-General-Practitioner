package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.CustomException;
import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.PatientNotFoundException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Notification;
import com.group15A.DataModel.Patient;

import java.util.List;

/**
 * Contains backend functionality to dynamically update the information on the home page
 *
 * @author Milovan Gveric
 */
public class HomeLogic implements IHome {
    private DataAccess dataAccessLayer;

    /**
     * Constructor for home logic
     * @throws DatabaseException if issues connecting to database
     */
    public HomeLogic() throws DatabaseException {
        this.dataAccessLayer = new DataAccess();
    }

    /**
     * Gets all the notifications (read and unread) associated with the passed in patient
     * @param patient the patient whose notifications you want to get
     * @return list of patient's notifications
     * @throws CustomException if issues with patient or notifications in database
     */
    @Override
    public List<Notification> getNotifications(Patient patient) throws CustomException {
        return this.dataAccessLayer.getNotifications(patient);
    }

    /**
     * Get the patient from the integer id
     * @param patientID
     * @return the patient
     * @throws DatabaseException if issues connecting to the database
     * @throws PatientNotFoundException if the patient with the passed in ID was not found
     */
    @Override
    public Patient getPatient(Integer patientID) throws DatabaseException, PatientNotFoundException {
        return this.dataAccessLayer.getPatient(patientID);
    }

    /**
     * Marks one of the patient's notifications as read, so it doesn't show up on the home
     * page next time it is visited
     * @param notification the notification to be marked as read (isNew = false)
     * @throws CustomException if issues in updating the notification record in the database
     */
    @Override
    public void readNotification(Notification notification) throws CustomException {
        this.dataAccessLayer.setNotificationSeen(notification);
    }
}
