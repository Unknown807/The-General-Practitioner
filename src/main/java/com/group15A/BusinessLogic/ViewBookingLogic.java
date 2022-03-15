package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.CustomException;
import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Booking;
import com.group15A.DataModel.Doctor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains backend functionality for getting bookings to display on the ViewBookingsPanel
 *
 * @author Milovan Gveric
 */
public class ViewBookingLogic implements IViewBooking {
    private DataAccess dataAccessLayer;

    /**
     * Constructor for the view booking logic
     * @throws DatabaseException if issues connecting to the database
     */
    public ViewBookingLogic() throws DatabaseException {
        this.dataAccessLayer = new DataAccess();
    }

    /**
     * Gets all the bookings belonging to the patient
     * @param patientID
     * @return the list of all the patient's bookings
     * @throws CustomException if issues getting bookings, or with patient
     */
    @Override
    public List<Booking> getBookings(Integer patientID) throws CustomException {
        List<Booking> patientBookings = this.dataAccessLayer.getBookings(this.dataAccessLayer.getPatient(patientID));
        List<Booking> patientNewBookings = new ArrayList<>();
        Timestamp today = new Timestamp(System.currentTimeMillis());

        // Remove all old bookings from the return list
        for (Booking b : patientBookings) {
            if (b.getBookingTime().after(today)) {
                patientNewBookings.add(b);
            }
        }

        return patientNewBookings;
    }

    /**
     * Gets the doctor that the patient is meeting with in the booking
     * @param doctorID
     * @return doctor involved in the booking
     * @throws CustomException if doctor is not found or database connection issues
     */
    @Override
    public Doctor getDoctor(Integer doctorID) throws CustomException {
        return this.dataAccessLayer.getDoctor(doctorID);
    }
}
