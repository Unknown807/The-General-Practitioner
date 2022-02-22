package com.group15A.BusinessLogic;

import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Patient;
import com.group15A.Validator.Validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterLogic implements IRegister {
    private Validator validator;
    private DataAccess dataAccessLayer;

    public RegisterLogic() throws Exception {
        this.dataAccessLayer = new DataAccess();
        this.validator = new Validator();
    }

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
