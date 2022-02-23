package com.group15A.BusinessLogic;

import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Patient;
import com.group15A.Session;
import com.group15A.Validator.Validator;

/**
 * Contains backend functionality that relates to signing the
 * user in
 *
 * @author Milovan Gveric
 * @author Wenbo Wu
 */
public class LogInLogic implements ILogIn {
    private DataAccess dataAccessLayer;
    private Validator validator;

    /**
     * Creates connection to DAL and has a validator class to
     * validate the user's email and password
     *
     * @throws DatabaseException if issue with connecting to database
     */
    public LogInLogic() throws DatabaseException {
        this.dataAccessLayer = new DataAccess();
        this.validator = new Validator();
    }

    /**
     * Verify the user's email and password format and
     * sign them in
     *
     * @param email
     * @param password
     * @throws Exception if issue with email or password format or getting the
     * patient from the database
     */
    @Override
    public void login(String email, String password, Boolean stayLoggedIn) throws Exception {
        this.validator.verifyEmail(email);
        this.validator.verifyPassword(password);

        //TODO login user with a Session instance or something
        Patient loggedInPatient = this.dataAccessLayer.getPatient(email, password);
        Session session = new Session(loggedInPatient,stayLoggedIn);
        session.saveToFile();
    }
}






