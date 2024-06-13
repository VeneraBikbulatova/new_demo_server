package com.example.demo.exceptions;

public class NotFoundService extends ServiceException{
    public NotFoundService(String message) {
        super(message);
    }

    public NotFoundService(String message, Throwable cause) {
        super(message, cause);
    }
}
