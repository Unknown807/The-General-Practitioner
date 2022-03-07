package com.group15A.DataModel;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *  Used to represent a notification within the system, will be used to pass and change
 *  information between the business logic and data access layer.
 *
 *  @author Wenbo Wu
 */
public class Notification {

    private Integer notifID;
    private Integer patientID;
    private String message;
    private String header;
    private Timestamp timestamp;
    private boolean isNew;

    public Notification(Integer notifID, Integer patientID, String header, String message, Timestamp timestamp) {
        this.notifID = notifID;
        this.patientID = patientID;
        this.header = header;
        this.message = message;
        this.timestamp = timestamp;
        isNew = true;
    }

    public Integer getNotifID() {
        return notifID;
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

    public String getHeader() {return header;}

    public void setHeader(String header) {this.header = header;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(notifID, that.notifID) && Objects.equals(patientID, that.patientID) && Objects.equals(message, that.message) && Objects.equals(header, that.header) && Objects.equals(timestamp, that.timestamp) && isNew==that.isNew;
    }

    @Override
    public int hashCode() {
        return Objects.hash(notifID, patientID, header, message, timestamp, isNew);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "NotifID=" + notifID +
                ", patientID=" + patientID +
                ", header='" + header + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", isNew=" + isNew +
                '}';
    }
}
