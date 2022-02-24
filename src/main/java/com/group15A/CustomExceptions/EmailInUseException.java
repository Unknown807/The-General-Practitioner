package com.group15A.CustomExceptions;

import com.group15A.Utils.ErrorCode;

import java.util.ArrayList;
import java.util.List;

public class EmailInUseException extends CustomException
{
    public EmailInUseException(String errorMessage) {
        super(errorMessage, new ArrayList<>(){{add(ErrorCode.EMAIL_IN_USE);}});
    }
}
