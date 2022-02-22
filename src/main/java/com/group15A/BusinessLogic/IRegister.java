package com.group15A.BusinessLogic;

public interface IRegister {
    public void register(String fName, String mName, String lName, String DoB, String gender, String phoneNo, String email, String confirmEmail, String password, String confirmPassword) throws Exception;
}
