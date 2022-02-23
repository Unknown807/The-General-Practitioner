package com.group15A.CustomExceptions;

import com.group15A.Utils.ErrorCode;

import java.util.ArrayList;

public class PatientNotFoundException extends CustomException
{
    public PatientNotFoundException()
    {
        super("The patient with the given details was not found.", new ArrayList<>(){{
            add(ErrorCode.USER_NOT_FOUND);
        }});
    }
}
