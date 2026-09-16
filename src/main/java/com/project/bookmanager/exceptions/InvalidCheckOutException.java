package com.project.bookmanager.exceptions;

public class InvalidCheckOutException extends RuntimeException {
    public InvalidCheckOutException(String message) {
        super(message);
    }
}
