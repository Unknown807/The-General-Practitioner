package com.group15A.DataModel;

import java.util.Date;
import java.util.Objects;

/**
 *  Used to represent a booking within the system, will be used to pass and change
 *  information between the business logic and data access layer.
 *
 *  @author Wenbo Wu
 */
// class for booking patient to booking appointment
public class Booking {

    private int bookingID;
    private int doctorID;
    private int patientID;
    private String bookingDate ;// the dates of booking
    private int state;// is show booked or  cancel

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
