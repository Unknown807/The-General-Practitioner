package com.group15A.BusinessLogic;

/**
 * The interface for RegisterLogic
 *
 * @author Milovan Gveric
 * @author Wenbo Wu
 */
public interface IRegister {
    public void register(String fName, String mName, String lName, String DoB, String gender, String phoneNo,
                         String email, String confirmEmail, String password, String confirmPassword, Integer chosenDoctor) throws Exception;
}
