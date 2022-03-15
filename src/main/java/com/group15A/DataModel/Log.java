package com.group15A.DataModel;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * Used to represent a log within the system, will be used to pass and change
 * information between the business logic and data access layer.
 *
 * @author Wenbo Wu
 */
public class Log {
    private Integer LogID;
    private String message;
    private Integer doctorID;
    private Integer patientID;
    private Integer bookingID;
    private Timestamp timestamp;



}
