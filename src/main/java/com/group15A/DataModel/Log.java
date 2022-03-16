package com.group15A.DataModel;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Used to represent a log within the system, will be used to pass and change
 * information between the business logic and data access layer.
 *
 * @author Wenbo Wu
 */
public class Log {
    private Integer logID;
    private String message;
    private Integer doctorID;
    private Integer patientID;
    private Integer bookingID;
    private Timestamp timestamp;

    /**
     * Constructor for a log
     *
     * @param logID the id
     * @param message the content of the log
     * @param doctorID  id for doctor
     * @param patientID id for patient
     * @param bookingID id for booking
     * @param timestamp time log has been created
     */
    public Log(Integer logID, String message, Integer doctorID, Integer patientID, Integer bookingID, Timestamp timestamp) {
        this.logID = logID;
        this.message = message;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.bookingID = bookingID;
        this.timestamp = timestamp;
    }

    public Integer getLogID() {return logID;}

    public void setLogID(Integer logID) {
        this.logID = logID;}

    public String getMessage() {return message;}

    public void setMessage(String message) {this.message = message;}

    public Integer getDoctorID() {return doctorID;}

    public void setDoctorID(Integer doctorID) {this.doctorID = doctorID;}

    public Integer getPatientID() {return patientID;}

    public void setPatientID(Integer patientID) {this.patientID = patientID;}

    public Integer getBookingID() {return bookingID;}

    public void setBookingID(Integer bookingID) {this.bookingID = bookingID;}

    public Timestamp getTimestamp() {return timestamp;}

    public void setTimestamp(Timestamp timestamp) {this.timestamp = timestamp;}

    /**
     * Method for equality testing
     *
     * @param o
     * @return whether object 'o' is equal to logs 'this'
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return Objects.equals(getLogID(), log.getLogID()) && Objects.equals(getMessage(), log.getMessage()) && Objects.equals(getDoctorID(), log.getDoctorID()) && Objects.equals(getPatientID(), log.getPatientID()) && Objects.equals(getBookingID(), log.getBookingID()) && Objects.equals(getTimestamp(), log.getTimestamp());
    }

    /**
     *  Hashing for logs object
     *
     * @return hashed object
     */
    @Override
    public int hashCode() {
        return Objects.hash(getLogID(), getMessage(), getDoctorID(), getPatientID(), getBookingID(), getTimestamp());
    }

    /**
     * toString method for logs
     *
     * @return a textual representation of logs and its data
     */
    @Override
    public String toString() {
        return "Log{" +
                "logID=" + logID +
                ", message='" + message + '\'' +
                ", doctorID=" + doctorID +
                ", patientID=" + patientID +
                ", bookingID=" + bookingID +
                ", timestamp=" + timestamp +
                '}';
    }
}
