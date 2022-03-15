package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.*;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Booking;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;
import com.group15A.Utils.DataModification;
import com.group15A.Utils.ErrorCode;
import com.group15A.Validator.Validator;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * Contains backend functionality that relates to adding new bookings for users
 *
 * @author Milovan Gveric
 */
public class AddBookingLogic implements IAddBooking {
    private Validator validator;
    private DataAccess dataAccessLayer;

    /**
     * Constructor for the add booking logic
     * @throws DatabaseException if there was an issue connecting to the database
     */
    public AddBookingLogic() throws DatabaseException {
        this.validator = new Validator();
        this.dataAccessLayer = new DataAccess();
    }

    /**
     * Verifies the new booking info and calls the DAL method to insert the new
     * booking into the database
     * @param date when the booking takes place
     * @param hour the hour the booking starts
     * @param minute the minute the booking starts
     * @param patientID which patient the booking relates to
     * @return the finalised booking and its details
     * @throws CustomException If any issues connecting to the database or creating the new booking
     */
    @Override
    public Booking createNewBooking(String date, String hour, String minute, Integer patientID) throws CustomException {
        ErrorCode timestampError = this.validator.verifyTimestamp(hour, minute);
        ErrorCode dateError = this.validator.verifyDate(date);

        // Check format of timestamp is correct
        if (timestampError != null || dateError != null) {
            throw new CustomException("Invalid time values", Arrays.asList(timestampError, dateError));
        }

        String timestamp = date+" "+hour+":"+minute+":00";
        ErrorCode impossibleDate = this.validator.verifyDateBeforeToday(timestamp);

        // Check that the booking is booked in the past
        if (impossibleDate != null) {
            throw new CustomException("Can't book on a past date", Arrays.asList(impossibleDate));
        }

        Patient patient = this.dataAccessLayer.getPatient(patientID);
        Doctor doctor = this.getPatientDoctor(patient);
        Timestamp bookingDateTime = Timestamp.valueOf(timestamp);

        // Finally, check if booking with the same time had already been made
        if (!this.verifyBookingIsNew(bookingDateTime, patient)) {
            throw new ExistingBookingException();
        }

        Booking newBooking = this.dataAccessLayer.createBooking(
                patient,
                doctor,
                bookingDateTime
        );

        // Show a new notification that the booking has been made to the user on the home panel
        this.dataAccessLayer.createNotification(patient, "Created New Booking", "Created a booking on "+ DataModification.fullDate(bookingDateTime)+" with Dr "+doctor.getFullName());

        return newBooking;
    }

    private Boolean verifyBookingIsNew(Timestamp bookingTime, Patient patient) throws CustomException {
        List<Booking> allPatientBookings = this.dataAccessLayer.getBookings(patient);

        for (Booking b : allPatientBookings) {
            if (b.getBookingTime().equals(bookingTime)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets the doctor associated with the passed in patient
     * @param patient
     * @return the patient's doctor
     * @throws DatabaseException if issues connecting to the database
     * @throws DoctorNotFoundException if the doctor was not found in the database
     */
    @Override
    public Doctor getPatientDoctor(Patient patient) throws DatabaseException, DoctorNotFoundException {
        return this.dataAccessLayer.getDoctor(patient);
    }

    /**
     * Get the patient from the integer id
     * @param patientID
     * @return the patient
     * @throws DatabaseException if issues connecting to the database
     * @throws PatientNotFoundException if the patient with the passed in ID was not found
     */
    @Override
    public Patient getPatient(Integer patientID) throws DatabaseException, PatientNotFoundException {
        return this.dataAccessLayer.getPatient(patientID);
    }


}
