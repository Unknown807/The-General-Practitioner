package com.group15A.DataModel;

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
    private Date bookingTime ;// the dates of booking
    private LocalTime timestamp;// is show booked or  cancel

    public Booking(Integer bookingID, Integer doctorID, Integer patientID, Date bookingTime, LocalTime timestamp) {
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

    public LocalTime getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(getBookingID(), booking.getBookingID()) && Objects.equals(getDoctorID(), booking.getDoctorID()) && Objects.equals(getPatientID(), booking.getPatientID()) && Objects.equals(getBookingTime(), booking.getBookingTime()) && Objects.equals(getTimestamp(), booking.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookingID(), getDoctorID(), getPatientID(), getBookingTime(), getTimestamp());
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
