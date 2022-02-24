package com.group15A.Utils;

/**
 * Error codes to be used with Custom Exceptions
 *
 * @author Andrei
 */
public enum ErrorCode
{
    // Form input errors
    WRONG_EMAIL, WRONG_PASSWORD, WRONG_DATE, WRONG_FIRST_NAME, WRONG_LAST_NAME, WRONG_MIDDLE_NAME, WRONG_GENDER, WRONG_PHONE_NO, WRONG_CONFIRMED_EMAIL, WRONG_CONFIRMED_PASSWORD,

    // Other errors
    DATABASE_ERROR, USER_NOT_FOUND
}
