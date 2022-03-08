package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.CustomException;
import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.DoctorNotFoundException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Booking;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;
import com.group15A.Utils.ErrorCode;
import com.group15A.Validator.Validator;

import javax.print.Doc;
import java.sql.Timestamp;
import java.util.Arrays;

public class AddBookingLogic implements IAddBooking {
    private Validator validator;
    private DataAccess dataAccessLayer;

    /**
     * Constructor for the doctor logic
     * @throws DatabaseException if there was an issue connecting to the database
     */
    public AddBookingLogic() throws DatabaseException {
        this.validator = new Validator();
        this.dataAccessLayer = new DataAccess();
    }

    @Override
    public Booking createNewBooking(String date, String hour, String minute, Integer patientID) throws CustomException {
        ErrorCode timestampError = this.validator.verifyTimestamp(hour, minute);
        ErrorCode dateError = this.validator.verifyDate(date);

        if (timestampError != null || dateError != null) {
            throw new CustomException("Invalid time values", Arrays.asList(timestampError, dateError));
        }

        Patient patient = this.dataAccessLayer.getPatient(patientID);
        Doctor doctor = this.getPatientDoctor(patient);
        Timestamp bookingDateTime = Timestamp.valueOf(date+" "+hour+":"+minute+":00");

        Booking newBooking = this.dataAccessLayer.createBooking(
                patient,
                doctor,
                bookingDateTime
        );

        return newBooking;
    }

    @Override
    public Doctor getPatientDoctor(Patient patient) throws DatabaseException, DoctorNotFoundException {
        return this.dataAccessLayer.getDoctor(patient);
    }

    @Override
    public Patient getPatient(Integer patientID) throws CustomException {
        return this.dataAccessLayer.getPatient(patientID);
    }


}
