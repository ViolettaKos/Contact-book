package com.example.phonecontacts.exception;

public class ServiceException extends Exception {
    public ServiceException() {
    }
    public ServiceException(String message) {
        super(message);
    }
}
