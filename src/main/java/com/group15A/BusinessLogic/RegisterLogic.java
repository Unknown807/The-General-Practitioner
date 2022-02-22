package com.group15A.BusinessLogic;

import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Patient;
import com.group15A.Validator.Validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
     * @throws Exception if issue connecting to the database
     */
    public RegisterLogic() throws Exception {
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
    public void register(String fName, String mName, String lName, String DoB, String gender, String phoneNo, String email, String confirmEmail, String password, String confirmPassword, Integer chosenDoctor) throws Exception {
        this.validator.verifyFirstName(fName);
        this.validator.verifyMiddleName(mName);
        this.validator.verifyLastName(lName);
        this.validator.verifyDoB(DoB);
        this.validator.verifyGender(gender);
        this.validator.verifyPhoneNo(phoneNo);
        this.validator.verifyEmail(email);
        this.validator.verifyPassword(password);
        this.validator.verifyMatchingEmails(email, confirmEmail);
        this.validator.verifyMatchingPasswords(password, confirmPassword);

        //TODO make methods for hashing password
        String passHash = password;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateConv = df.parse(DoB);

        Patient newPatient = this.dataAccessLayer.registerPatient(
                new Patient(email, passHash, fName, mName, lName, dateConv, gender, phoneNo),
                dataAccessLayer.getDoctors().get(chosenDoctor)
        );
    }

}
