package com.group15A.CustomExceptions;

import com.group15A.Utils.ErrorCode;

import java.util.List;

/**
 * Thrown when null data was sent for a non-nullable value in the database
 * @author Andrei Constantin
 */
public class NullDataException extends CustomException
{
    public NullDataException(String errorMessage)
    {
        super(errorMessage);
    }
}
