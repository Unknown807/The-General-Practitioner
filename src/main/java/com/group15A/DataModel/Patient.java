package com.group15A.DataModel;

/**
 * Used to represent a patient within the system, will be used to pass and change
 * information between the business logic and data access layer.
 * @author Milovan Gveric
 * @author Wenbo Wu
 */
public class Patient {
    private Integer patientID;
    private String email;
    private String passHash;
    private String firstName;
    private String middleName;
    private String lastName;
    private String dob;
    private String gender;
    private String phoneNo;

    public static final Integer UNKNOWN_PATIENT_ID = -1;

    /**
     * Constructor for a patient
     * @param patientID The ID
     * @param email The email address
     * @param passHash The hashed password
     * @param firstName The first name
     * @param middleName The middle name
     * @param lastName The last name
     * @param dob The date of birth
     * @param gender The gender
     * @param phoneNo The phone number
     */
    public Patient(Integer patientID, String email, String passHash, String firstName, String middleName, String lastName, String dob, String gender, String phoneNo) {
        this.patientID = patientID;
        this.email = email;
        this.passHash = passHash;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.phoneNo = phoneNo;
    }

    /**
     * Constructor for a patient with an unknown id
     * @param email The email address
     * @param passHash The hashed password
     * @param firstName The first name
     * @param middleName The middle name
     * @param lastName The last name
     * @param dob The date of birth
     * @param gender The gender
     * @param phoneNo The phone number
     */
    public Patient(String email, String passHash, String firstName, String middleName, String lastName, String dob, String gender, String phoneNo)
    {
        this(UNKNOWN_PATIENT_ID, email, passHash, firstName, middleName, lastName, dob, gender, phoneNo);
    }


    // All getters and setters for attributes below

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPatientID() {
        return patientID;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
