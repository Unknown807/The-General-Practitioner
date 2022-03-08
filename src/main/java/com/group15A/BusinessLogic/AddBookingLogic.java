package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.DoctorNotFoundException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Booking;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;

import java.sql.Timestamp;
import java.util.Date;

public class AddBookingLogic implements IAddBooking {
    private DataAccess dataAccessLayer;

    /**
     * Constructor for the doctor logic
     * @throws DatabaseException if there was an issue connecting to the database
     */
    public AddBookingLogic() throws DatabaseException {
        this.dataAccessLayer = new DataAccess();
    }

    @Override
    public Booking createNewBooking(Date bookingDate, Timestamp bookingTime, Patient patient) throws Exception {
        System.out.println("Created booking");
        return null;
    }

    @Override
    public Doctor getPatientDoctor(Patient patient) throws DatabaseException, DoctorNotFoundException {
        return this.dataAccessLayer.getDoctor(patient);
    }


}
