package com.group15A.DataModel;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

/**
 *  Used to represent a booking within the system, will be used to pass and change
 *  information between the business logic and data access layer.
 *
 *  @author Wenbo Wu
 */
public class Booking {

    private Integer bookingID;
    private Integer doctorID;
    private Integer patientID;
    private Date bookingTime;
    private Timestamp timestamp;

    public Booking(Integer bookingID, Integer doctorID, Integer patientID, Date bookingTime, Timestamp timestamp) {
        this.bookingID = bookingID;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.bookingTime = bookingTime;
        this.timestamp = timestamp;
    }

    public Integer getBookingID() {
        return bookingID;
    }

    public void setBookingID(Integer bookingID) {
        this.bookingID = bookingID;
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

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookingID, booking.bookingID) && Objects.equals(doctorID, booking.doctorID) && Objects.equals(patientID, booking.patientID) && Objects.equals(bookingTime, booking.bookingTime) && Objects.equals(timestamp, booking.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingID, doctorID, patientID, bookingTime, timestamp);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + bookingID +
                ", doctorID=" + doctorID +
                ", patientID=" + patientID +
                ", bookingTime=" + bookingTime +
                ", timestamp=" + timestamp +
                '}';
    }
}
