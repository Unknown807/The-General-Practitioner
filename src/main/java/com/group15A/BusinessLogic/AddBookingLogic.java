package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.*;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Booking;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;
import com.group15A.Utils.DataModification;
import com.group15A.Utils.ErrorCode;
import com.group15A.Validator.Validator;

import java.sql.Time;
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
    public void createNewBooking(String date, String hour, String minute, String type, Integer patientID) throws CustomException {
        this.correctDateTimeFormat(hour, minute, date);
        this.correctBookingType(type);

        String timestamp = date+" "+hour+":"+minute+":00";
        this.isImpossibleBooking(timestamp);

        Patient patient = this.dataAccessLayer.getPatient(patientID);
        Doctor doctor = this.getPatientDoctor(patient);
        Timestamp bookingDateTime = Timestamp.valueOf(timestamp);

        this.isNewBooking(bookingDateTime, patient);

        Booking newBooking = this.dataAccessLayer.createBooking(
                patient,
                doctor,
                bookingDateTime,
                type
        );

        // Show a new notification that the booking has been made to the user on the home panel
        this.dataAccessLayer.createNotification(patient, "Created New Booking", "Created a booking on "+ DataModification.fullDate(bookingDateTime)+" with Dr "+doctor.getFullName());

        // Create a log to register the scheduling of a booking
        this.dataAccessLayer.createLog(patient, "Patient "+patient.getFirstName()+" "+patient.getLastName()+" has scheduled a booking with Dr. " + dataAccessLayer.getDoctor(patient).getLastName() + " on " + DataModification.shortDateTime(newBooking.getBookingTime()));
    }

    private void correctBookingType(String type) throws CustomException {
        ErrorCode wrongType = this.validator.verifyBookingType(type);

        if (wrongType != null) {
            throw new CustomException("Wrong booking type", Arrays.asList(wrongType));
        }
    }

    private void isImpossibleBooking(String timestamp) throws CustomException {
        ErrorCode impossibleDate = this.validator.verifyDateBeforeToday(timestamp);

        // Check that the booking is booked in the past
        if (impossibleDate != null) {
            throw new CustomException("Can't book on a past date", Arrays.asList(impossibleDate));
        }
    }

    private void isNewBooking(Timestamp bookingDateTime, Patient patient) throws CustomException {
        // Finally, check if booking with the same time had already been made
        if (!this.verifyBookingIsNew(bookingDateTime, patient)) {
            throw new ExistingBookingException();
        }
    }

    private void correctDateTimeFormat(String hour, String minute, String date) throws CustomException {
        ErrorCode timestampError = this.validator.verifyTimestamp(hour, minute);
        ErrorCode dateError = this.validator.verifyDate(date);

        // Check format of timestamp is correct
        if (timestampError != null || dateError != null) {
            throw new CustomException("Invalid time values", Arrays.asList(timestampError, dateError));
        }
    }

    /**
     * Updates the booking information in the database for the newly rescheduled booking
     * @param date new date
     * @param hour new hour
     * @param minute new minute
     * @param patientID
     * @param booking booking to be edited and then updated in the database
     * @throws CustomException If any issues connecting to the database or updating the booking
     */
    @Override
    public void rescheduleBooking(String date, String hour, String minute, String type, Integer patientID, Booking booking) throws CustomException {
        this.correctDateTimeFormat(hour, minute, date);
        this.correctBookingType(type);

        Timestamp oldBookingTime = booking.getBookingTime();
        String oldBookingTimestamp = DataModification.fullDate(oldBookingTime);

        booking.setBookingTime(Timestamp.valueOf(date+" "+hour+":"+minute+":00"));
        booking.setType(type);
        this.isImpossibleBooking(booking.getBookingTime().toString());
        Patient patient = this.dataAccessLayer.getPatient(patientID);
        Doctor doctor = this.getPatientDoctor(patient);
        this.isNewBooking(booking.getBookingTime(), patient);

        booking = this.dataAccessLayer.updateBooking(booking);
        this.dataAccessLayer.createNotification(
                patient,
                "Rescheduled Booking",
                "Changed booking with Dr "+doctor.getFullName()+", from "+oldBookingTimestamp+
                        " to "+DataModification.fullDate(booking.getBookingTime())
        );

        // Create a log to register the rescheduling of a booking
        this.dataAccessLayer.createLog(patient, "Patient "+patient.getFirstName()+" "+patient.getLastName()+" has rescheduled a booking with Dr. " + dataAccessLayer.getDoctor(patient).getLastName() + " from " + DataModification.shortDateTime(oldBookingTime) + " to " + DataModification.shortDateTime(booking.getBookingTime()));
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
     * @throws InvalidDataException if the patient is invalid
     * @throws NullDataException if the patient is null
     * @throws DatabaseException if issues connecting to the database
     * @throws DoctorNotFoundException if the doctor was not found in the database
     */
    @Override
    public Doctor getPatientDoctor(Patient patient) throws NullDataException, DatabaseException, DoctorNotFoundException, InvalidDataException
    {
        return this.dataAccessLayer.getDoctor(patient);
    }

    /**
     * Get the patient from the integer id
     * @param patientID
     * @return the patient
     * @throws InvalidDataException if the data is invalid
     * @throws DatabaseException if issues connecting to the database
     * @throws PatientNotFoundException if the patient with the passed in ID was not found
     */
    @Override
    public Patient getPatient(Integer patientID) throws DatabaseException, PatientNotFoundException, InvalidDataException
    {
        return this.dataAccessLayer.getPatient(patientID);
    }


}
