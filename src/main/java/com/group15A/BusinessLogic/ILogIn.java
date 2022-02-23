package com.group15A.BusinessLogic;

/**
 * The interface for LogInLogic
 *
 * @author Milovan Gveric
 * @author Wenbo Wu
 */
public interface ILogIn {
    public void login(String email, String password, Boolean stayLoggedIn) throws Exception;
}
