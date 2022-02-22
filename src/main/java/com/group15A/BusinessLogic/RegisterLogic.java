package com.group15A.BusinessLogic;

import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Patient;
import com.group15A.GUI.RegisterPanel;
import org.apache.commons.validator.GenericValidator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Pattern;

public class RegisterLogic implements IRegister {
    private final Pattern containsUpperCase = Pattern.compile("^(?=.*[a-z]).+$");
    private final Pattern containsLowerCase = Pattern.compile("^(?=.*[A-Z]).+$");
    private final Pattern containsDigit = Pattern.compile("^(?=.*\\d).+$");
    private final Pattern containsSpecials = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);

    private RegisterPanel registerPanel;
    private DataAccess dataAccessLayer;

    public RegisterLogic(RegisterPanel registerPanel) throws Exception {
        this.registerPanel = registerPanel;
        this.dataAccessLayer = new DataAccess();
    }

    @Override
    public void register(String fName, String mName, String lName, String DoB, String gender, String phoneNo, String email, String confirmEmail, String password, String confirmPassword) throws Exception {
        this.verifyFirstName(fName);
        this.verifyMiddleName(mName);
        this.verifyLastName(lName);
        this.verifyDoB(DoB);
        this.verifyGender(gender);
        this.verifyPhoneNo(phoneNo);
        this.verifyEmail(email);
        this.verifyPassword(password);
        this.verifyMatchingEmails(email, confirmEmail);
        this.verifyMatchingPasswords(password, confirmPassword);

        //TODO make methods for hashing password
        String passHash = password;

        //TODO register new patient

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateConv = df.parse(DoB);

        Patient newPatient = this.dataAccessLayer.registerPatient(
                new Patient(email, passHash, fName, mName, lName, dateConv, gender, phoneNo),
                //TODO change the line below to use the doctor selected in the user interface
                dataAccessLayer.getDoctors().get(0)
        );
    }

    private Boolean isAlpha(String str) {
        return str.chars().allMatch(Character::isLetter);
    }

    private Boolean verifyName(String name) {
        return (name.isBlank() || !this.isAlpha(name));
    }

    private void verifyFirstName(String fName) throws IllegalArgumentException {
        if (verifyName(fName)) {
            //TODO set error labels in UI to visible
            throw new IllegalArgumentException();
        }
    }

    private void verifyMiddleName(String mName) throws IllegalArgumentException {
        if (!this.isAlpha(mName)) {
            //TODO set error labels in UI to visible
            throw new IllegalArgumentException();
        }
    }

    private void verifyLastName(String lName) throws IllegalArgumentException {
        if (verifyName(lName)) {
            //TODO set error labels in UI to visible
            throw new IllegalArgumentException();
        }
    }

    private void verifyDoB(String DoB) throws IllegalArgumentException {
        if (!GenericValidator.isDate(DoB, "yyyy-MM-dd", true)) {
            //TODO set error labels in UI to visible
            throw new IllegalArgumentException();
        }
    }

    private void verifyGender(String gender) throws IllegalArgumentException {
        if (!gender.equals("M") && !gender.equals("F") && !gender.equals("Other")) {
            //TODO set error labels in UI to visible
            throw new IllegalArgumentException();
        }
    }

    private void verifyPhoneNo(String phoneNo) throws IllegalArgumentException {
        if (!GenericValidator.isInt(phoneNo) || !GenericValidator.isInRange(phoneNo.length(), 5, 15) || phoneNo.isBlank()) {
            //TODO set error labels in UI to visible
            throw new IllegalArgumentException();
        }
    }

    private void verifyEmail(String email) throws IllegalArgumentException {
        if (!GenericValidator.isEmail(email)) {
            //TODO set error labels in UI to visible
            throw new IllegalArgumentException();
        }
    }

    private void verifyPassword(String password) throws IllegalArgumentException {
        // must have min 8 chars, 1 upper, 1 lower, 1 digit, 1 special character
        Boolean invalidLength = password.length() < 8;
        Boolean noUpperCase = !containsUpperCase.matcher(password).matches();
        Boolean noLowerCase = !containsLowerCase.matcher(password).matches();
        Boolean noDigit = !containsDigit.matcher(password).matches();
        Boolean noSpecials = !containsSpecials.matcher(password).find();



        if (invalidLength || noUpperCase || noLowerCase || noDigit || noSpecials) {
            //TODO set error labels in UI to visible
            throw new IllegalArgumentException();
        }

    }

    private void verifyMatchingEmails(String email, String emailConfirmation) throws IllegalArgumentException {
        if (!email.equals(emailConfirmation)) {
            //TODO set error labels in UI to visible
            throw new IllegalArgumentException();
        }
    }

    private void verifyMatchingPasswords(String password, String passwordConfirmation) throws IllegalArgumentException {
        if (!password.equals(passwordConfirmation)) {
            //TODO set error labels in UI to visible
            throw new IllegalArgumentException();
        }
    }

}
