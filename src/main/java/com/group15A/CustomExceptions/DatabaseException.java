package com.group15A.CustomExceptions;

import com.group15A.Utils.ErrorCode;

import java.util.ArrayList;

public class DatabaseException extends CustomException
{
    public DatabaseException(String errorMessage)
    {
        super(errorMessage, new ArrayList<>(){{add(ErrorCode.DATABASE_ERROR);}});
    }
}
