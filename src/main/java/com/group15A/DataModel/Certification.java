package com.group15A.DataModel;

import java.util.Objects;

/**
 * Used to represent a doctor certification within the system, will be used to pass and change
 * information between the business logic and data access layer.
 * @author Milovan Gveric
 * @author Wenbo Wu
 */
public class Certification {
    private Integer doctorID;
    private Integer certID;
    private String name;
    private String field;
    private String dateObtained;

    // Constructor
    public Certification(Integer doctorID, Integer certID, String name, String field, String dateObtained) {
        this.doctorID = doctorID;
        this.certID = certID;
        this.name = name;
        this.field = field;
        this.dateObtained = dateObtained;
    }

    @Override
    public String toString() {
        return "Certification{" +
                "doctorID=" + doctorID +
                ", certID=" + certID +
                ", name='" + name + '\'' +
                ", field='" + field + '\'' +
                ", dateObtained='" + dateObtained + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certification that = (Certification) o;
        return doctorID.equals(that.doctorID) && certID.equals(that.certID) && name.equals(that.name) && field.equals(that.field) && dateObtained.equals(that.dateObtained);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorID, certID, name, field, dateObtained);
    }

    // All getters and setters for attributes below

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDateObtained() {
        return dateObtained;
    }

    public void setDateObtained(String dateObtained) {
        this.dateObtained = dateObtained;
    }

    public Integer getCertID() {
        return certID;
    }

    public Integer getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Integer doctorID) {
        this.doctorID = doctorID;
    }
}
