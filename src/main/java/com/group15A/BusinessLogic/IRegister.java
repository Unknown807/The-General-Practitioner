package com.group15A.BusinessLogic;

import com.group15A.DataModel.Doctor;

public interface IRegister {
    public void register(String fName, String mName, String lName, String DoB, String gender, String phoneNo, String email, String confirmEmail, String password, String confirmPassword, Integer chosenDoctor) throws Exception;
}
