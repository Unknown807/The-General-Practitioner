package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.CustomException;
import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.DoctorNotFoundException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Booking;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;

import java.util.List;

/**
 * @author Milovan Gveric
 */
public class ViewBookingLogic implements IViewBooking {
    private DataAccess dataAccessLayer;

    public ViewBookingLogic() throws DatabaseException {
        this.dataAccessLayer = new DataAccess();
    }

    @Override
    public List<Booking> getBookings(Integer patientID) throws CustomException {
        return this.dataAccessLayer.getBookings(
                this.dataAccessLayer.getPatient(patientID)
        );
    }

    @Override
    public Doctor getDoctor(Integer doctorID) throws CustomException {
        return this.dataAccessLayer.getDoctor(doctorID);
    }
}
