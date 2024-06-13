package com.example.demo.exceptions;

public class NotFoundRepository extends RepositoryException{
    public NotFoundRepository(String message) {
        super(message);
    }

    public NotFoundRepository(String message, Throwable cause) {
        super(message, cause);
    }
}
