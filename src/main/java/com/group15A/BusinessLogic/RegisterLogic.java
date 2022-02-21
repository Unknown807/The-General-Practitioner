package com.group15A.BusinessLogic;

import com.group15A.GUI.RegisterPanel;
import org.apache.commons.validator.GenericValidator;

public class RegisterLogic implements IRegister {
    private RegisterPanel registerPanel;

    public RegisterLogic(RegisterPanel registerPanel) {
        this.registerPanel = registerPanel;
    }

    @Override
    public void register(String fName, String mName, String lName, String DoB, String gender, String phoneNo, String email, String confirmEmail, String password, String confirmPassword) throws Exception {
        this.verifyFirstName(fName);
        this.verifyMiddleName(mName);
        this.verifyLastName(lName);
        //TODO register new patient
        //this.verifyLogin(fName, mName, lName, DoB, gender, phoneNo, email, confirmEmail, password, confirmPassword);

    }

//    private void verifyLogin(String fName, String mName, String lName, String DoB, String gender, String phoneNo, String email, String confirmEmail, String password, String confirmPassword) {
//
//    }

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

    }

    private void verifyEmail(String email) {

    }

    private void verifyMatchingEmails(String email, String emailConfirmation) {

    }

    private void verifyPassword(String password) {

    }

    private void verifyMatchingPasswords(String password, String passwordConfirmation) {

    }

}
