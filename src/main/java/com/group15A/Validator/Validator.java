package com.group15A.Validator;

import org.apache.commons.validator.GenericValidator;

import java.util.regex.Pattern;

public class Validator {
    private final Pattern containsUpperCase = Pattern.compile("^(?=.*[a-z]).+$");
    private final Pattern containsLowerCase = Pattern.compile("^(?=.*[A-Z]).+$");
    private final Pattern containsDigit = Pattern.compile("^(?=.*\\d).+$");
    private final Pattern containsSpecials = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);

    private Boolean isAlpha(String str) {
        return str.chars().allMatch(Character::isLetter);
    }

    private Boolean verifyName(String name) {
        return (name.isBlank() || !this.isAlpha(name));
    }

    public void verifyFirstName(String fName) throws IllegalArgumentException {
        if (this.verifyName(fName)) {
            throw new IllegalArgumentException();
        }
    }

    public void verifyMiddleName(String mName) throws IllegalArgumentException {
        if (!this.isAlpha(mName)) {
            throw new IllegalArgumentException();
        }
    }

    public void verifyLastName(String lName) throws IllegalArgumentException {
        if (this.verifyName(lName)) {
            throw new IllegalArgumentException();
        }
    }

    public void verifyDoB(String DoB) throws IllegalArgumentException {
        if (!GenericValidator.isDate(DoB, "yyyy-MM-dd", false)) {
            throw new IllegalArgumentException();
        }
    }

    public void verifyGender(String gender) throws IllegalArgumentException {
        if (!gender.equals("M") && !gender.equals("F") && !gender.equals("Other")) {
            throw new IllegalArgumentException();
        }
    }

    public void verifyPhoneNo(String phoneNo) throws IllegalArgumentException {
        if (!GenericValidator.isInt(phoneNo) || !GenericValidator.isInRange(phoneNo.length(), 5, 15) || phoneNo.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    public void verifyEmail(String email) throws IllegalArgumentException {
        if (!GenericValidator.isEmail(email)) {
            throw new IllegalArgumentException();
        }
    }

    public void verifyPassword(String password) throws IllegalArgumentException {
        // must have min 8 chars, 1 upper, 1 lower, 1 digit, 1 special character
        Boolean invalidLength = password.length() < 8;
        Boolean noUpperCase = !containsUpperCase.matcher(password).matches();
        Boolean noLowerCase = !containsLowerCase.matcher(password).matches();
        Boolean noDigit = !containsDigit.matcher(password).matches();
        Boolean noSpecials = !containsSpecials.matcher(password).find();

        if (invalidLength || noUpperCase || noLowerCase || noDigit || noSpecials) {
            throw new IllegalArgumentException();
        }

    }

    public void verifyMatchingEmails(String email, String confirmEmail) throws IllegalArgumentException {
        if (!email.equals(confirmEmail)) {
            throw new IllegalArgumentException();
        }
    }

    public void verifyMatchingPasswords(String password, String confirmPassword) throws IllegalArgumentException {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException();
        }
    }

}
