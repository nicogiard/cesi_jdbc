package com.company.models.exception;

public class SQLServiceException extends Exception {

    public SQLServiceException(String reason, Exception e) {
        super(reason, e);
    }

}
