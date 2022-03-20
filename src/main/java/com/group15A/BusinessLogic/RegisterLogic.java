package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.CustomException;
import com.group15A.CustomExceptions.InvalidDataException;
import com.group15A.CustomExceptions.NullDataException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;
import com.group15A.Utils.DataModification;
import com.group15A.Utils.ErrorCode;
import com.group15A.Validator.Validator;
import org.mindrot.jbcrypt.BCrypt;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

/**
 * Contains backend functionality that relates to registering
 * new patients
 *
 * @author Milovan Gveric
 * @author Wenbo Wu
 */
public class RegisterLogic implements IRegister {
    private DataAccess dataAccessLayer;
    private Validator validator;

    /**
     * The constructor for the RegisterLogic class.
     * Creates connection to DAL and creates a validator to
     * validate the user's login information
     *
     * @throws DatabaseException if there was an issue connecting to the database
     */
    public RegisterLogic() throws DatabaseException {
        this.dataAccessLayer = new DataAccess();
        this.validator = new Validator();
    }

    public void registerNotification(Patient patient) throws InvalidDataException, NullDataException, DatabaseException {
        dataAccessLayer.createNotification(
                patient,
                "Welcome to the GP, "+patient.getFirstName(),
                "Your patient account has been created."
                );
    }

    /**
     * Validates all fields and then inserts the new patient in the database
     *
     * @param fName first name
     * @param mName middle name
     * @param lName last name
     * @param DoB date of birth
     * @param gender gender
     * @param phoneNo phone number
     * @param email email
     * @param confirmEmail confirmation email
     * @param password password
     * @param confirmPassword confirmation password
     * @param chosenDoctor the index of the doctor in the list that will correspond to their ID
     *                     in the database
     * @throws CustomException if any verification method fails or there was an error inserting a patient into the database
     */
    @Override
    public Patient register(String fName, String mName, String lName, String DoB, String gender, String phoneNo, String email, String confirmEmail, String password, String confirmPassword, Doctor chosenDoctor) throws CustomException {

        // Capitalize alphabetic strings
        fName = DataModification.capitalize(fName);
        mName = DataModification.capitalize(mName);
        lName = DataModification.capitalize(lName);
        email = email.toLowerCase();
        confirmEmail = confirmEmail.toLowerCase();

        Stream<ErrorCode> errorsStream = Stream.of(
                this.validator.verifyFirstName(fName),
                this.validator.verifyMiddleName(mName),
                this.validator.verifyLastName(lName),
                this.validator.verifyDate(DoB),
                this.validator.verifyGender(gender),
                this.validator.verifyPhoneNo(phoneNo),
                this.validator.verifyEmail(email),
                this.validator.verifyPassword(password),
                this.validator.verifyMatchingEmails(email, confirmEmail),
                this.validator.verifyMatchingPasswords(password, confirmPassword),
                (chosenDoctor == null) ? ErrorCode.DOCTOR_NOT_CHOSEN : null
        );

        // Checks if any errors occurred and passes the list of them to display in the UI
        List<ErrorCode> errorsList = errorsStream.filter(Objects::nonNull).collect(Collectors.toList());
        if (errorsList.size() > 0) {
            throw new CustomException("Invalid Form Details", errorsList);
        }

        // Attempt to convert to Date object from SimpleDateFormat, as new Date(String) is deprecated
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateConv;
        try {
            dateConv = df.parse(DoB);
        } catch (ParseException e) {
            throw new CustomException("Invalid date", Arrays.asList(ErrorCode.WRONG_DATE));
        }

        // Hash password
        String passHash = BCrypt.hashpw(password, BCrypt.gensalt());

        Patient loggedInPatient = this.dataAccessLayer.registerPatient(
                new Patient(email, passHash, fName, mName, lName, dateConv, gender, phoneNo),
                chosenDoctor
        );

        return loggedInPatient;
    }
}
