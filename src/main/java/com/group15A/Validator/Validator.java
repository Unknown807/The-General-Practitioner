package com.group15A.Validator;

import com.group15A.CustomExceptions.CustomException;
import com.group15A.Utils.ErrorCode;
import org.apache.commons.validator.GenericValidator;

import java.util.regex.Pattern;

public class Validator {
    private final Pattern containsUpperCase = Pattern.compile("^(?=.*[a-z]).+$");
    private final Pattern containsLowerCase = Pattern.compile("^(?=.*[A-Z]).+$");
    private final Pattern containsDigit = Pattern.compile("^(?=.*\\d).+$");
    private final Pattern containsSpecials = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);

    public Boolean isAlpha(String str) {
        return str.chars().allMatch(Character::isLetter);
    }

    public Boolean isNum(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    public Boolean verifyName(String name) {
        return (name.isBlank() || !this.isAlpha(name));
    }

    public ErrorCode verifyFirstName(String fName) {
        if (this.verifyName(fName)) {
            return ErrorCode.WRONG_FIRST_NAME;
        }
        return null;
    }

    public ErrorCode verifyMiddleName(String mName){
        if (!this.isAlpha(mName)) {
            return ErrorCode.WRONG_MIDDLE_NAME;
        }
        return null;
    }

    public ErrorCode verifyLastName(String lName) {
        if (this.verifyName(lName)) {
            return ErrorCode.WRONG_LAST_NAME;
        }
        return null;
    }

    public ErrorCode verifyDoB(String DoB) {
        if (!GenericValidator.isDate(DoB, "yyyy-MM-dd", false)) {
            return ErrorCode.WRONG_DATE;
        }
        return null;
    }

    public ErrorCode verifyGender(String gender) {
        if (!gender.equals("M") && !gender.equals("F") && !gender.equals("Other")) {
            return ErrorCode.WRONG_GENDER;
        }
        return null;
    }

    public ErrorCode verifyPhoneNo(String phoneNo) {
        if (!this.isNum(phoneNo) || !GenericValidator.isInRange(phoneNo.length(), 5, 15) || phoneNo.isBlank()) {
            return ErrorCode.WRONG_PHONE_NO;
        }
        return null;
    }

    public ErrorCode verifyEmail(String email) {
        if (!GenericValidator.isEmail(email)) {
            return ErrorCode.WRONG_EMAIL;
        }
        return null;
    }

    public ErrorCode verifyPassword(String password) {
        // must have min 8 chars, 1 upper, 1 lower, 1 digit, 1 special character
        Boolean invalidLength = password.length() < 8;
        Boolean noUpperCase = !containsUpperCase.matcher(password).matches();
        Boolean noLowerCase = !containsLowerCase.matcher(password).matches();
        Boolean noDigit = !containsDigit.matcher(password).matches();
        Boolean noSpecials = !containsSpecials.matcher(password).find();

        if (invalidLength || noUpperCase || noLowerCase || noDigit || noSpecials) {
            return ErrorCode.WRONG_PASSWORD;
        }

        return null;
    }

    public ErrorCode verifyMatchingEmails(String email, String confirmEmail) {
        if (!email.equals(confirmEmail)) {
            return ErrorCode.WRONG_CONFIRMED_EMAIL;
        }

        return null;
    }

    public ErrorCode verifyMatchingPasswords(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return ErrorCode.WRONG_CONFIRMED_PASSWORD;
        }

        return null;
    }

}
