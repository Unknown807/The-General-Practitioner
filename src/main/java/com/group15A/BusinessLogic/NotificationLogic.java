package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.PatientNotFoundException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Notification;
import com.group15A.Session;

import java.util.List;

public class NotificationLogic
{
    DataAccess dataAccess;
    public NotificationLogic() throws DatabaseException {
        dataAccess = new DataAccess();
    }

    public List<Notification> getNotifications(Session session) throws DatabaseException, PatientNotFoundException {
        return dataAccess.getNotifications(dataAccess.getPatient(session.getLoggedInPatientID()));
    }
}
