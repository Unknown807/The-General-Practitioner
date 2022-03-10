package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.CustomException;
import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.NotificationNotFoundException;
import com.group15A.CustomExceptions.PatientNotFoundException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Notification;
import com.group15A.DataModel.Patient;

import java.util.List;

/**
 * @author Milovan Gveric
 */
public class HomeLogic implements IHome {
    private DataAccess dataAccessLayer;

    public HomeLogic() throws DatabaseException {
        this.dataAccessLayer = new DataAccess();
    }

    @Override
    public List<Notification> getNotifications(Patient patient) throws CustomException {
        return this.dataAccessLayer.getNotifications(patient);
    }

    @Override
    public Patient getPatient(Integer patientID) throws DatabaseException, PatientNotFoundException {
        return this.dataAccessLayer.getPatient(patientID);
    }

    @Override
    public void readNotification(Notification notification) throws CustomException {
        this.dataAccessLayer.setNotificationSeen(notification);
    }
}
