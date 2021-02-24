package com.assignment.Newys.exceptions;

public class NotFoundInDbException extends RuntimeException {
    public NotFoundInDbException() {
        super();
    }

    public NotFoundInDbException(String message) {
        super(message);
    }

    public NotFoundInDbException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundInDbException(Throwable cause) {
        super(cause);
    }

    protected NotFoundInDbException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
