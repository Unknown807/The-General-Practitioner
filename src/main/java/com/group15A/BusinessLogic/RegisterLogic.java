package com.group15A.BusinessLogic;

import com.group15A.GUI.RegisterPanel;
import org.apache.commons.validator.GenericValidator;

import java.util.regex.Pattern;

public class RegisterLogic implements IRegister {
    private final Pattern containsUpperCase = Pattern.compile("^(?=.*[a-z]).+$");
    private final Pattern containsLowerCase = Pattern.compile("^(?=.*[A-Z]).+$");
    private final Pattern containsDigit = Pattern.compile("^(?=.*\\d).+$");
    private final Pattern containsSpecials = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);

    private RegisterPanel registerPanel;

    public RegisterLogic(RegisterPanel registerPanel) {
        this.registerPanel = registerPanel;
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

        //TODO register new patient

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

    private void verifyDoB(String DoB) {
        if (!GenericValidator.isDate(DoB, "yyyy-MM-dd", true)) {
            //TODO set error labels in UI to visible
            throw new IllegalArgumentException();
        }
    }

    private void verifyGender(String gender) {
        if (gender != "M" || gender != "F" || gender != "Other") {
            //TODO set error labels in UI to visible
            throw new IllegalArgumentException();
        }
    }

    private void verifyPhoneNo(String phoneNo) {
        if (!GenericValidator.isInt(phoneNo) || !GenericValidator.isInRange(phoneNo.length(), 5, 15) || phoneNo.isBlank()) {
            //TODO set error labels in UI to visible
            throw new IllegalArgumentException();
        }
    }

    private void verifyEmail(String email) {
        if (!GenericValidator.isEmail(email)) {
            //TODO set error labels in UI to visible
            throw new IllegalArgumentException();
        }
    }

    private void verifyPassword(String password) {
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

    private void verifyMatchingEmails(String email, String emailConfirmation) {
        if (!email.equals(emailConfirmation)) {
            //TODO set error labels in UI to visible
            throw new IllegalArgumentException();
        }
    }

    private void verifyMatchingPasswords(String password, String passwordConfirmation) {
        if (!password.equals(passwordConfirmation)) {
            //TODO set error labels in UI to visible
            throw new IllegalArgumentException();
        }
    }

}
