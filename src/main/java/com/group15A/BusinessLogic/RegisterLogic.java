package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.CustomException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Patient;
import com.group15A.Session;
import com.group15A.Utils.ErrorCode;
import com.group15A.Validator.Validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Contains backend functionality that relates to registering
 * new patients
 *
 * @author Milovan Gveric
 * @author Wenbo Wu
 */
public class RegisterLogic implements IRegister {
    private Validator validator;
    private DataAccess dataAccessLayer;

    /**
     * Constructor
     *
     * @throws DatabaseException if issue connecting to the database
     */
    public RegisterLogic() throws DatabaseException {
        this.dataAccessLayer = new DataAccess();
        this.validator = new Validator();
    }

    /**
     * Validates all fields are of the correct format and then inserts the new patient
     * in the database
     *
     * @param fName first name
     * @param mName middle name
     * @param lName last name
     * @param DoB date of birth
     * @param gender
     * @param phoneNo
     * @param email
     * @param confirmEmail
     * @param password
     * @param confirmPassword
     * @param chosenDoctor the index of the doctor in the list that will correspond to their ID
     *                     in the database
     * @throws Exception if any verification method fails or when inserting a patient into the database
     */
    @Override
    public void register(String fName, String mName, String lName, String DoB, String gender, String phoneNo, String email, String confirmEmail, String password, String confirmPassword, Integer chosenDoctor) throws CustomException {
        Stream<ErrorCode> errorsStream = Stream.of(
                this.validator.verifyFirstName(fName),
                this.validator.verifyMiddleName(mName),
                this.validator.verifyLastName(lName),
                this.validator.verifyDoB(DoB),
                this.validator.verifyGender(gender),
                this.validator.verifyPhoneNo(phoneNo),
                this.validator.verifyEmail(email),
                this.validator.verifyPassword(password),
                this.validator.verifyMatchingEmails(email, confirmEmail),
                this.validator.verifyMatchingPasswords(password, confirmPassword)
            );

        List<ErrorCode> errorsList = errorsStream.filter(x -> x!=null).collect(Collectors.toList());
        if (errorsList.size() > 0) {
            throw new CustomException("Invalid Form Details", errorsList);
        }

        //TODO make methods for hashing password
        String passHash = password;


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateConv = null;
        try {
            dateConv = df.parse(DoB);
        } catch (ParseException e) {
            throw new CustomException("Invalid date", Arrays.asList(ErrorCode.WRONG_DATE));
        }

        Patient newPatient = this.dataAccessLayer.registerPatient(
                new Patient(email, passHash, fName, mName, lName, dateConv, gender, phoneNo),
                dataAccessLayer.getDoctors().get(chosenDoctor)
        );

        Patient loggedInPatient = this.dataAccessLayer.getPatient(email, password); // Does not use `passHash`
        Session session = new Session(loggedInPatient, false);
        session.saveToFile();
    }

}
