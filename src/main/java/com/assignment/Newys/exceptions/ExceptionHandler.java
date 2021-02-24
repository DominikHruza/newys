package com.assignment.Newys.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = NotFoundInDbException.class)
    public ResponseEntity<Object> handleResourceNotFound(
            NotFoundInDbException exception) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ErrorResponse errorResponse = new ErrorResponse(
                notFound,
                exception.getMessage()
        );
        return new ResponseEntity<>(errorResponse, notFound);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = DuplicateResourceEntryException.class)
    public ResponseEntity<Object> handleResourceNotFound(
            DuplicateResourceEntryException exception) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(
                badRequest,
                exception.getMessage()
        );
        return new ResponseEntity<>(errorResponse, badRequest);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = UserRoleUnauthorizedException.class)
    public ResponseEntity<Object> UserRoleUnauthorizedException(
            DuplicateResourceEntryException exception) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(
                badRequest,
                exception.getMessage()
        );
        return new ResponseEntity<>(errorResponse, badRequest);
    }
}
