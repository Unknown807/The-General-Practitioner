package com.group15A.BusinessLogic;

import com.group15A.GUI.RegisterPanel;

public class RegisterLogic implements IRegister {
    private RegisterPanel registerPanel;

    public RegisterLogic(RegisterPanel registerPanel) {
        this.registerPanel = registerPanel;
    }

    @Override
    public Boolean register(String fName, String mName, String lName, String DoB, String gender, String phoneNo, String email, String confirmEmail, String password, String confirmPassword) throws Exception {
        System.out.println("Clicked Register");
        return null;
    }

}
