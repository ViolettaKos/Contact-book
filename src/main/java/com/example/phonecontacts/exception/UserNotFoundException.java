package com.example.phonecontacts.exception;

public class UserNotFoundException extends ServiceException{
    private static final String USER_NOT_FOUND = "User with such login does not exists";

    public UserNotFoundException() {
        super(USER_NOT_FOUND);
    }
}
