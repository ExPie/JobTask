package com.studentIS.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BadAuthException extends RuntimeException {

    public BadAuthException() {
        super("Invalid authentication");
    }
}
