package com.group15A.BusinessLogic;

import com.group15A.Session;

/**
 * The interface for LogInLogic
 *
 * @author Milovan Gveric
 * @author Wenbo Wu
 */
public interface ILogIn {
    public Session login(String email, String password, Boolean stayLoggedIn) throws Exception;
}
