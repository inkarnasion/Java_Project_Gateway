package com.example.java_project_gateway.exception;

public class DuplicatedRequestIdException extends Exception {
    public DuplicatedRequestIdException(String message) {
        super(message);
    }
}
