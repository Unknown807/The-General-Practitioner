package com.group15A.DataModel;

import java.util.Date;
import java.util.Objects;

/**
 *  Used to represent a notification within the system, will be used to pass and change
 *  information between the business logic and data access layer.
 *
 *  @author Wenbo Wu
 */
//class for the patient get message
public class Notification {

    private int NotificationID;
    private int doctorID;
    private int patientID;
    private String Notification; // the message

}
