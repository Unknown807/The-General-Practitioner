package com.group15A.DataModel;

import java.util.Objects;

/**
 * Used to represent a doctor within the system, will be used to pass and change
 * information between the business logic and data access layer.
 * @author Milovan Gveric
 * @author Wenbo Wu
 */

public class Doctor {
    private Integer doctorID;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String dob;
    private String gender;
    private String phoneNo;

    // Constructor

    public Doctor(Integer doctorID, String email, String firstName, String middleName, String lastName, String dob, String gender, String phoneNo) {
        this.doctorID = doctorID;
        this.email = email;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorID=" + doctorID +
                ", email='" + email + '\'' +
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
        Doctor doctor = (Doctor) o;
        return doctorID.equals(doctor.doctorID) && email.equals(doctor.email) && firstName.equals(doctor.firstName) && Objects.equals(middleName, doctor.middleName) && lastName.equals(doctor.lastName) && dob.equals(doctor.dob) && gender.equals(doctor.gender) && phoneNo.equals(doctor.phoneNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorID, email, firstName, middleName, lastName, dob, gender, phoneNo);
    }

    // All getters and setters for attributes below

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDoctorID() {
        return doctorID;
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
