package com.group15A.CustomExceptions;

public class PatientNotFoundException extends Exception
{
    public PatientNotFoundException()
    {
        super("The patient with the given details was not found.");
    }
}
