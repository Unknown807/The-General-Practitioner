package com.group15A.Utils;

/**
 * The type of data to expected to be received by the current pages
 *
 * @author Milovan Gveric
 */
public enum ReceiveType {
    // Register Page
    DOCTOR,

    // Choose Doctor Page
    RETURN_PAGE,

    // View Booking Page
    PATIENT_ID,

    // For Dynamic Events
    EVENT
}
