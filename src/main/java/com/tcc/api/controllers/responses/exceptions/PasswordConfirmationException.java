package com.tcc.api.controllers.responses.exceptions;

public class PasswordConfirmationException extends RuntimeException{
    public PasswordConfirmationException(String message){
        super(message);
    }
}
