package com.assignment.Newys.exceptions;

public class UserRoleUnauthorizedException extends RuntimeException {

    public UserRoleUnauthorizedException() {
    }

    public UserRoleUnauthorizedException(String message) {
        super(message);
    }

    public UserRoleUnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRoleUnauthorizedException(Throwable cause) {
        super(cause);
    }

    public UserRoleUnauthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
