package com.group15A.DataModel;

import java.util.Objects;

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

    // Constructor

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

    @Override
    public String toString() {
        return "Patient{" +
                "patientID=" + patientID +
                ", email='" + email + '\'' +
                ", passHash='" + passHash + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return patientID.equals(patient.patientID) && email.equals(patient.email) && passHash.equals(patient.passHash) && firstName.equals(patient.firstName) && Objects.equals(middleName, patient.middleName) && lastName.equals(patient.lastName) && dob.equals(patient.dob) && gender.equals(patient.gender) && phoneNo.equals(patient.phoneNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientID, email, passHash, firstName, middleName, lastName, dob, gender, phoneNo);
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
