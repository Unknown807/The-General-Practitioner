package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Patient;
import com.group15A.Session;
import com.group15A.CustomExceptions.CustomException;
import com.group15A.Utils.ErrorCode;
import com.group15A.Validator.Validator;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Arrays;

/**
 * Contains backend functionality that relates to signing the user in
 *
 * @author Milovan Gveric
 * @author Wenbo Wu
 */
public class LogInLogic implements ILogIn {
    private DataAccess dataAccessLayer;
    private Validator validator;

    /**
     * The constructor for the LogInLogic class.
     * Creates connection to DAL and creates a validator to
     * validate the user's login information
     *
     * @throws DatabaseException if there was an issue connecting to the database
     */
    public LogInLogic() throws DatabaseException {
        this.dataAccessLayer = new DataAccess();
        this.validator = new Validator();
    }

    /**
     * Verify the format of the user's email and password and
     * sign them in if the validation passes
     *
     * @param email The email account
     * @param password The password
     * @throws Exception if there was an issue with the format of the email or password or with retrieving the
     * patient from the database
     */
    @Override
    public Session login(String email, String password, Boolean stayLoggedIn) throws Exception {
//        ErrorCode passError = this.validator.verifyEmail(email);
//        ErrorCode emailError = this.validator.verifyPassword(password);
//
//        // doesn't matter if null is passed in as the same error is made visible in the ui if an error is caught regardless
//        if (passError != null || emailError != null) {
//            throw new CustomException("Invalid Email or Password in LogIn", Arrays.asList(passError, emailError));
//        }

        String passHash = BCrypt.hashpw(password, BCrypt.gensalt()); // can remove while testing UI
        Patient loggedInPatient = this.dataAccessLayer.getPatient(email);
        Session session = new Session(loggedInPatient, stayLoggedIn);
        session.saveToFile();
        return session;
    }
}






