package com.group15A.BusinessLogic;

import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Patient;
import com.group15A.Validator.Validator;

public class LogInLogic implements ILogin {
    private DataAccess dataAccessLayer;
    private Validator validator;

    public LogInLogic() throws Exception {
        this.dataAccessLayer = new DataAccess();
        this.validator = new Validator();
    }

    @Override
    public void login(String email, String password) throws Exception {
        this.validator.verifyEmail(email);
        this.validator.verifyPassword(password);

        //TODO login user with a Session instance or something
        Patient loggedInPatient = this.dataAccessLayer.getPatient(email, password);
    }
}






