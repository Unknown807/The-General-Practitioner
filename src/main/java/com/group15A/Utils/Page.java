package com.group15A.Utils;
import java.util.HashMap;

/**
 * Used for the showPage method in multiPanelWindow
 *
 * @author Milovan Gveric
 */
public enum Page {
    LOGIN,
    REGISTER,
    HOME,
    CHOOSE_DOCTOR;

    public final static HashMap<Page, String> panels = new HashMap<>(){{
        put(LOGIN, "logInPanel");
        put(REGISTER, "registerPanel");
        put(HOME, "homePanel");
        put(CHOOSE_DOCTOR, "chooseDoctorPanel");
    }};

    public final static HashMap<Page, String> titles = new HashMap<>(){{
        put(LOGIN, "Please Sign In");
        put(REGISTER, "Enter Your Details");
        put(HOME, "Welcome");
        put(CHOOSE_DOCTOR, "Choose your new doctor");
    }};
}
