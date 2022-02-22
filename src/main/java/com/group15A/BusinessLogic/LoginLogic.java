package com.group15A.BusinessLogic;

import com.group15A.GUI.LogInPanel;

public class LoginLogic implements ILogin {
    private LogInPanel logInPanel;

    public LoginLogic(LogInPanel logInPanel) {
        this.logInPanel = logInPanel;
    }

    @Override
    public Boolean login(String email, String password) throws Exception {
        System.out.println("Clicked LogIn");
        return null;
    }
}






