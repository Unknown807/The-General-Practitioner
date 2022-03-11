package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.CustomException;
import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.DoctorNotFoundException;
import com.group15A.CustomExceptions.PatientNotFoundException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Booking;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;
import com.group15A.Utils.DataModification;
import com.group15A.Utils.ErrorCode;
import com.group15A.Utils.JWidgetShortcuts;
import com.group15A.Validator.Validator;

import java.sql.Timestamp;
import java.util.Arrays;

/**
 * @author Milovan Gveric
 */
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

        String timestamp = date+" "+hour+":"+minute+":00";
        ErrorCode impossibleDate = this.validator.verifyDateBeforeToday(timestamp);

        if (impossibleDate != null) {
            throw new CustomException("Can't book on a past date", Arrays.asList(impossibleDate));
        }

        Patient patient = this.dataAccessLayer.getPatient(patientID);
        Doctor doctor = this.getPatientDoctor(patient);
        Timestamp bookingDateTime = Timestamp.valueOf(timestamp);

        Booking newBooking = this.dataAccessLayer.createBooking(
                patient,
                doctor,
                bookingDateTime
        );

        this.dataAccessLayer.createNotification(patient, "Created New Booking", "Created a booking on "+ DataModification.fullDate(bookingDateTime)+" with Dr "+doctor.getFullName());

        return newBooking;
    }

    @Override
    public Doctor getPatientDoctor(Patient patient) throws DatabaseException, DoctorNotFoundException {
        return this.dataAccessLayer.getDoctor(patient);
    }

    @Override
    public Patient getPatient(Integer patientID) throws DatabaseException, PatientNotFoundException {
        return this.dataAccessLayer.getPatient(patientID);
    }


}
