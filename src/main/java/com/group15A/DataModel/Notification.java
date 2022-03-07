package com.group15A.DataModel;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

/**
 *  Used to represent a notification within the system, will be used to pass and change
 *  information between the business logic and data access layer.
 *
 *  @author Wenbo Wu
 */
public class Notification {

    private Integer NotifID;
    private Integer doctorID;
    private Integer patientID;
    private String message;
    private Timestamp timestamp;
    private boolean isNew;

    public Notification(Integer notifID, Integer doctorID, Integer patientID, String message, Timestamp timestamp) {
        NotifID = notifID;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.message = message;
        this.timestamp = timestamp;
        isNew = true;
    }

    public Integer getNotifID() {
        return NotifID;
    }

    public Integer getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Integer doctorID) {
        this.doctorID = doctorID;
    }

    public Integer getPatientID() {
        return patientID;
    }

    public void setPatientID(Integer patientID) {
        this.patientID = patientID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public boolean getIsNew()
    {
        return isNew;
    }

    public void setIsNew(boolean isNew) {this.isNew = isNew;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(NotifID, that.NotifID) && Objects.equals(doctorID, that.doctorID) && Objects.equals(patientID, that.patientID) && Objects.equals(message, that.message) && Objects.equals(timestamp, that.timestamp) && isNew==that.isNew;
    }

    @Override
    public int hashCode() {
        return Objects.hash(NotifID, doctorID, patientID, message, timestamp, isNew);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "NotifID=" + NotifID +
                ", doctorID=" + doctorID +
                ", patientID=" + patientID +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", isNew=" + isNew +
                '}';
    }
}
