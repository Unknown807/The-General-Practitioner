package com.group15A.DataModel;

/**
 * Used to represent a doctor certification within the system, will be used to pass and change
 * information between the business logic and data access layer.
 * @author Milovan Gveric
 * @author Wenbo Wu
 */
public class Certification {
    private String name;
    private String field;
    private String dateObtained;

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
}
