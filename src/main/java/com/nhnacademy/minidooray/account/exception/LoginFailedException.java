package com.nhnacademy.minidooray.account.exception;

public class LoginFailedException extends RuntimeException{
    public LoginFailedException(){}
    public LoginFailedException(String msg){
        super(msg);
    }
}
