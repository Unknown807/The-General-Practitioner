package com.group15A.BusinessLogic;

import com.group15A.DataModel.Notification;
import com.group15A.DataModel.Patient;
import com.group15A.Session;

import java.util.List;

/**
 * The interface for HomeLogic
 *
 * @author Milovan Gveric
 */
public interface IHome {
    public List<Notification> getNotifications(Patient patient) throws Exception;
    public Patient getPatient(Integer patientID) throws Exception;
}
