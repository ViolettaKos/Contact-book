package com.example.phonecontacts.exception;

public class DuplicatedLoginException extends ServiceException {
    private static final String DUPLICATE_LOGIN = "User already exists with such login";

    public DuplicatedLoginException() {
        super(DUPLICATE_LOGIN);
    }
}
