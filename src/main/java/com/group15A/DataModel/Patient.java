package com.group15A.DataModel;

/**
 * Used to represent a patient within the system, will be used to pass and change
 * information between the business logic and data access layer.
 * @author Milovan Gveric
 * @author Wenbo Wu
 */
public class Patient {
    private String email;
    private String passHash;
    private String firstName;
    private String middleName;
    private String lastName;
    private String dob;
    private String gender;
    private String phoneNo;

    // All getters and setters for attributes below

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
