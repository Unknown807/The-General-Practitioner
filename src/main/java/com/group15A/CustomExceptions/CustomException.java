package com.group15A.CustomExceptions;

import com.group15A.Utils.ErrorCode;

import java.util.ArrayList;
import java.util.List;

public class CustomException extends Exception
{
    private final List<ErrorCode> errorList;

    public CustomException(String errorMessage, List<ErrorCode> errorList)
    {
        super(errorMessage);
        this.errorList = errorList;
    }

    public CustomException(String errorMessage)
    {
        super(errorMessage);
        this.errorList = new ArrayList<>();
    }

    public List<ErrorCode> getErrorList()
    {
        return errorList;
    }
}
